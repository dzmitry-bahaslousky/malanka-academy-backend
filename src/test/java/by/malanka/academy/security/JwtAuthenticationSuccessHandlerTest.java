package by.malanka.academy.security;

import by.malanka.academy.dto.AuthTokenResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("JWT Authentication Success Handler Tests")
class JwtAuthenticationSuccessHandlerTest {

    @InjectMocks
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Mock
    private AuthTokenGenerationService authTokenGenerationService;

    @Test
    @DisplayName("test response media type")
    void testResponseMediaType() throws IOException {
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        jwtAuthenticationSuccessHandler.onAuthenticationSuccess(
                new MockHttpServletRequest(),
                mockHttpServletResponse,
                mock(Authentication.class)
        );

        assertThat(mockHttpServletResponse.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    @DisplayName("test response body")
    void testResponseBody() throws IOException {
        when(authTokenGenerationService.generate(any(Authentication.class)))
                .thenReturn(new AuthTokenResponseDto("accessToken", "refreshToken"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        jwtAuthenticationSuccessHandler.onAuthenticationSuccess(
                new MockHttpServletRequest(),
                mockHttpServletResponse,
                mock(Authentication.class)
        );

        assertResponseBody(mockHttpServletResponse.getContentAsString());
    }

    private void assertResponseBody(String body) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);
        assertThat(jsonNode.get("access_token").asText()).isEqualTo("accessToken");
        assertThat(jsonNode.get("refresh_token").asText()).isEqualTo("refreshToken");
    }

}
