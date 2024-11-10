package by.malanka.academy.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("JWT Generator Service Tests")
class JwtGeneratorServiceTest {

    private JwtGeneratorService jwtGeneratorService;

    @Mock
    private JwtEncoder jwtEncoder;

    @Captor
    private ArgumentCaptor<JwtEncoderParameters> jwtEncoderParametersCaptor;

    private final long jwtTokenLifetime = 100;

    @BeforeEach
    void setUp() {
        jwtGeneratorService = new JwtGeneratorService(jwtEncoder, jwtTokenLifetime);
    }

    @Test
    @DisplayName("test jwt claims keys")
    void testGenerateTokenClaims() {
        Authentication mockAuthentication = mockAuthentication();
        mockJwtEncoderGetTokenValue();

        jwtGeneratorService.generate(mockAuthentication);

        verify(jwtEncoder).encode(jwtEncoderParametersCaptor.capture());
        assertJwtClaimsKeys(jwtEncoderParametersCaptor.getValue().getClaims());
    }

    @Test
    @DisplayName("test jwt lifetime")
    void testGenerateTokenLifetime() {
        Authentication mockAuthentication = mockAuthentication();
        mockJwtEncoderGetTokenValue();

        jwtGeneratorService.generate(mockAuthentication);

        verify(jwtEncoder).encode(jwtEncoderParametersCaptor.capture());
        assertJwtLifetime(jwtEncoderParametersCaptor.getValue().getClaims());
    }

    private Authentication mockAuthentication() {
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("name");
        when(mockAuthentication.getAuthorities()).thenReturn(List.of());
        return mockAuthentication;
    }

    private void mockJwtEncoderGetTokenValue() {
        Jwt jwt = mock(Jwt.class);
        when(jwtEncoder.encode(any(JwtEncoderParameters.class))).thenReturn(jwt);
        when(jwt.getTokenValue()).thenReturn("token");
    }

    private static void assertJwtClaimsKeys(JwtClaimsSet claims) {
        Map<String, Object> claimsMap = claims.getClaims();
        assertThat(claimsMap).containsKeys("iss", "sub", "exp", "iat", "roles");
    }

    private void assertJwtLifetime(JwtClaimsSet claims) {
        Duration tokenLifetime = Duration.between(claims.getIssuedAt(), claims.getExpiresAt());
        assertThat(tokenLifetime.toSeconds()).isEqualTo(jwtTokenLifetime);
    }

}