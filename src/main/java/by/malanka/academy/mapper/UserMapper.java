package by.malanka.academy.mapper;

import by.malanka.academy.dto.author.AuthorDto;
import by.malanka.academy.dto.register.CreateUserDto;
import by.malanka.academy.entity.RoleEntity;
import by.malanka.academy.entity.UserEntity;
import org.mapstruct.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class UserMapper {
    protected final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "roles", expression = "java(java.util.Set.of(roleEntity))")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.password()))")
    public abstract UserEntity toEntity(CreateUserDto dto, RoleEntity roleEntity);

    public abstract AuthorDto toAuthorDto(UserEntity userEntity);

    public abstract User toUserDetails(UserEntity userEntity);

    @ObjectFactory
    protected User createUser(UserEntity userEntity) {
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIsActive(),
                true,
                true,
                true,
                userEntity.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList())
        );
    }

}