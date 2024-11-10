package by.malanka.academy.security;

import by.malanka.academy.AbstractIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@DisplayName("User Details Service Integration Tests")
public class UserDetailsServiceImplIntegrationTest extends AbstractIntegrationTest {
    private final UserDetailsServiceImpl userDetailsService;

    @Test
    @DisplayName("load user details")
    void loadUserDetails() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("test");

        assertThat(userDetails.getUsername()).isEqualTo("test");
        assertThat(userDetails.getPassword()).contains("{bcrypt}");
        assertThat(userDetails.getAuthorities()).hasSize(1);
    }

    @Test
    @DisplayName("when user doesn't exist")
    void whenUserDoesNotExist() {
        assertThatThrownBy(() -> userDetailsService.loadUserByUsername("not exist"))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("Failed to load user: not exist");
    }

}
