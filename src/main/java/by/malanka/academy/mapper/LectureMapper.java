package by.malanka.academy.mapper;

import by.malanka.academy.dto.lecture.LectureDto;
import by.malanka.academy.entity.LectureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LectureMapper {
    LectureDto toDto(LectureEntity lectureEntity);
}