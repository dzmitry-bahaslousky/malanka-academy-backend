package by.malanka.academy.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Refresh Token Provider Unit Tests")
class RefreshTokenProviderServiceTest {
    RefreshTokenProviderService refreshTokenProviderService;

    @BeforeEach
    void setUp() {
        refreshTokenProviderService = new RefreshTokenProviderService();
    }

    @Nested
    @DisplayName("generateRefreshToken() method tests")
    class GenerateRefreshTokenTests {

        @Test
        @DisplayName("test generate refresh token")
        void testGenerateRefreshToken() {
            String token = refreshTokenProviderService.generate("test");

            assertThat(token).isEqualTo("098f6bcd4621d373cade4e832627b4f6");
        }

    }

}
