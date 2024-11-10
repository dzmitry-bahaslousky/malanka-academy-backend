package by.malanka.academy.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;

    public JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        validateRequest(request);
        UsernamePasswordAuthenticationToken authenticationToken = createAuthentication(request);
        setDetails(request, authenticationToken);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(HttpServletRequest request) {
        Map<String, String> loginRequest = parseRequestBody(request);
        return unauthenticated(loginRequest.get(this.getUsernameParameter()), loginRequest.get(this.getPasswordParameter()));
    }

    private static void validateRequest(HttpServletRequest request) {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
    }

    @SneakyThrows(IOException.class)
    private Map<String, String> parseRequestBody(HttpServletRequest request) {
        return objectMapper.readValue(request.getInputStream(), new TypeReference<HashMap<String, String>>() {
        });
    }

}
