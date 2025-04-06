package by.malanka.academy.security;

import by.malanka.academy.mapper.UserMapper;
import by.malanka.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDetails)
                .orElseThrow(() -> throwUsernameNotFoundException(username));
    }

    private UsernameNotFoundException throwUsernameNotFoundException(String username) {
        UsernameNotFoundException exception = new UsernameNotFoundException("Failed to load user: " + username);
        log.error(exception.getMessage(), exception);
        return exception;
    }

}
