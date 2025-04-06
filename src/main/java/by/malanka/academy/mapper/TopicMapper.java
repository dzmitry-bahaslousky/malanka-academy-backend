package by.malanka.academy.mapper;

import by.malanka.academy.dto.topic.TopicDto;
import by.malanka.academy.entity.TopicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = TopicItemMapper.class
)
public interface TopicMapper {
    @Mapping(target = "items", source = "topicItems")
    TopicDto toDto(TopicEntity topicEntity);
}