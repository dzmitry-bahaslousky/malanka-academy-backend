package by.malanka.academy.security;

import by.malanka.academy.dto.AuthTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthTokenGenerationService {
    private final JwtGeneratorService jwtGeneratorService;
    private final RefreshTokenProviderService refreshTokenProviderService;

    public AuthTokenResponseDto generate(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return generate(userDetails);
        }
        throw new AuthenticationServiceException("Invalid authentication principal");
    }

    public AuthTokenResponseDto generate(UserDetails userDetails) {
        String accessToken = jwtGeneratorService.generate(userDetails);
        String refreshToken = refreshTokenProviderService.generate(accessToken);
        return new AuthTokenResponseDto(accessToken, refreshToken);
    }

}
