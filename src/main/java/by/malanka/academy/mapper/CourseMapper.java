package by.malanka.academy.mapper;

import by.malanka.academy.dto.course.CourseDetailsDto;
import by.malanka.academy.dto.course.CoursePreviewDto;
import by.malanka.academy.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {TopicMapper.class, UserMapper.class}
)
public interface CourseMapper {

    @Mapping(target = "category", source = "category.name")
    CoursePreviewDto toPreviewDto(CourseEntity courseEntity);

    @Mapping(target = "category", source = "category.name")
    CourseDetailsDto toDetailsDto(CourseEntity courseEntity);

}