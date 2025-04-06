package by.malanka.academy.security;

import by.malanka.academy.dto.AuthTokenResponseDto;
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
    private final AuthTokenGenerationService authTokenGenerationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationSuccessHandler(AuthTokenGenerationService authTokenGenerationService) {
        this.authTokenGenerationService = authTokenGenerationService;
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {
        AuthTokenResponseDto loginResponseDto = authTokenGenerationService.generate(authentication);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(loginResponseDto));
    }

}
