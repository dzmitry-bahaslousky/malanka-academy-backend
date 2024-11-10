package by.malanka.academy.security;

import by.malanka.academy.AbstractIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@DisplayName("JSON Username Password Authentication Integration Tests")
public class JsonUsernamePasswordAuthenticationIntegrationTest extends AbstractIntegrationTest {
    private final MockMvc mockMvc;

    @Test
    @DisplayName("success login")
    void testLoginRequest() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "test",
                                  "password": "test"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.access_token").isNotEmpty(),
                        jsonPath("$.refresh_token").isNotEmpty()
                );
    }

    @Test
    @DisplayName("when incorrect login request data")
    void testLoginRequestWhenIncorrectRequestData() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "incorrect",
                                  "password": "incorrect"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

}
