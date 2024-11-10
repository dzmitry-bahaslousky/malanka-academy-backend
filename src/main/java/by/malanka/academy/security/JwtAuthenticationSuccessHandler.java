package by.malanka.academy.security;

import by.malanka.academy.dto.LoginResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtGeneratorService jwtGeneratorService;
    private final RefreshTokenProviderService refreshTokenProviderService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationSuccessHandler(
            RefreshTokenProviderService refreshTokenProviderService,
            JwtGeneratorService jwtGeneratorService) {
        this.refreshTokenProviderService = refreshTokenProviderService;
        this.jwtGeneratorService = jwtGeneratorService;
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        String accessToken = jwtGeneratorService.generate(authentication);
        String refreshToken = refreshTokenProviderService.generate(accessToken);
        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, refreshToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(loginResponseDto));
    }

}
