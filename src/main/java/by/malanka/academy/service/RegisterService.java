package by.malanka.academy.service;

import by.malanka.academy.dto.AuthTokenResponseDto;
import by.malanka.academy.dto.register.CreateUserDto;
import by.malanka.academy.entity.UserEntity;
import by.malanka.academy.mapper.UserMapper;
import by.malanka.academy.security.AuthTokenGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthTokenGenerationService authTokenGenerationService;

    @Transactional
    public AuthTokenResponseDto register(CreateUserDto dto) {
        UserEntity userEntity = userService.create(dto);

        return authTokenGenerationService.generate(userMapper.toUserDetails(userEntity));
    }

}
