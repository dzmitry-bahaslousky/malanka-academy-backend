package by.malanka.academy.mapper;

import by.malanka.academy.dto.topic.TopicItemDto;
import by.malanka.academy.entity.TopicItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicItemMapper {
    TopicItemDto toDto(TopicItemEntity topicItemEntity);

}