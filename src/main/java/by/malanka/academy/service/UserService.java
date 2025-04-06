package by.malanka.academy.service;

import by.malanka.academy.dto.register.CreateUserDto;
import by.malanka.academy.entity.RoleEntity;
import by.malanka.academy.entity.UserEntity;
import by.malanka.academy.mapper.UserMapper;
import by.malanka.academy.repository.RoleRepository;
import by.malanka.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserEntity create(CreateUserDto dto) {
        RoleEntity roleEntity = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role does not exist"));

        return userRepository.save(userMapper.toEntity(dto, roleEntity));
    }

}
