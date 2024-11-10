package by.malanka.academy.service;

import by.malanka.academy.entity.RoleEntity;
import by.malanka.academy.entity.UserEntity;
import by.malanka.academy.repository.UserRepository;
import by.malanka.academy.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Details Service Tests")
class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("success load user details")
    void testLoadUserDetails() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(buildUserEntity()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(anyString());

        assertThat(userDetails).isNotNull();
    }

    @Test
    @DisplayName("when user not found")
    void testLoadUserDetailsWhenNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(anyString()))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("Failed to load user:");
    }

    private UserEntity buildUserEntity() {
        return UserEntity.builder()
                .username("username")
                .password("password")
                .roles(Set.of(RoleEntity.builder()
                        .name("USER")
                        .build()))
                .build();
    }

}
