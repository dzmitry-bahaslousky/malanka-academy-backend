package by.malanka.academy.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtGeneratorService {
    private final JwtEncoder jwtEncoder;
    private final long tokenValidityInSeconds;

    public JwtGeneratorService(
            JwtEncoder jwtEncoder,
            @Value("${application.security.access-token.expiry-in-seconds}") long tokenValidityInSeconds) {
        this.jwtEncoder = jwtEncoder;
        this.tokenValidityInSeconds = tokenValidityInSeconds;
    }

    public String generate(Authentication authentication) {
        JwtClaimsSet claims = buildJwtClaims(authentication);
        JwtEncoderParameters from = JwtEncoderParameters.from(claims);
        return jwtEncoder.encode(from).getTokenValue();
    }

    private JwtClaimsSet buildJwtClaims(Authentication authentication) {
        String roles = fetchRolesFromAuthentication(authentication);

        Instant issuedAt = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("self")
                .subject(authentication.getName())
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(tokenValidityInSeconds))
                .claim("roles", roles)
                .build();
    }

    private static String fetchRolesFromAuthentication(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

}
