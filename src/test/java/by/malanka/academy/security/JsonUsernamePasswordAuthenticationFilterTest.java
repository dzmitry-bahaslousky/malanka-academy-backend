package by.malanka.academy.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

@DisplayName("JSON Username Password Authentication Filter Tests")
class JsonUsernamePasswordAuthenticationFilterTest {
    private JsonUsernamePasswordAuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        filter = new JsonUsernamePasswordAuthenticationFilter(mock(AuthenticationManager.class));
    }

    @Test
    @DisplayName("when not supported http method")
    void testAttemptAuthenticationWhenNotSupportedHttpMethod() {
        assertThatThrownBy(() -> filter.attemptAuthentication(new MockHttpServletRequest(), new MockHttpServletResponse()))
                .isInstanceOf(AuthenticationServiceException.class)
                .hasMessageContaining("Authentication method not supported:");
    }

    @Test
    @DisplayName("test correct request authentication")
    void test() {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setContent("""
                {
                  "username": "test",
                  "password": "test"
                }
                """.getBytes());

        filter.attemptAuthentication(mockHttpServletRequest, new MockHttpServletResponse());
    }

}
