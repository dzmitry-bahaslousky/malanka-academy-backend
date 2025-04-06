package by.malanka.academy.mapper;

import by.malanka.academy.dto.author.AuthorDto;
import by.malanka.academy.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UserMapper {

    AuthorDto toAuthorDto(UserEntity userEntity);

}