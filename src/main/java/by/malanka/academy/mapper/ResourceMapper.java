package by.malanka.academy.mapper;

import by.malanka.academy.dto.resource.ResourceDto;
import by.malanka.academy.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResourceMapper {
    ResourceDto toDto(ResourceEntity resourceEntity);

}