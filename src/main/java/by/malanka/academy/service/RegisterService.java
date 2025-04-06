package by.malanka.academy.service;

import by.malanka.academy.dto.AuthTokenResponseDto;
import by.malanka.academy.dto.register.CreateUserDto;
import by.malanka.academy.repository.RoleRepository;
import by.malanka.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthTokenResponseDto register(CreateUserDto request) {
        return null;
    }

}
