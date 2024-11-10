package by.malanka.academy.security;

import by.malanka.academy.entity.RoleEntity;
import by.malanka.academy.entity.UserEntity;
import by.malanka.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> throwUsernameNotFoundException(username));
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles().stream()
                        .map(RoleEntity::getName)
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }

    private UsernameNotFoundException throwUsernameNotFoundException(String username) {
        UsernameNotFoundException exception = new UsernameNotFoundException("Failed to load user: " + username);
        log.error(exception.getMessage(), exception);
        return exception;
    }

}
