package by.malanka.academy.service;

import by.malanka.academy.dto.PageWrapper;
import by.malanka.academy.dto.course.CourseDetailsDto;
import by.malanka.academy.dto.course.CoursePreviewDto;
import by.malanka.academy.dto.topic.TopicDto;
import by.malanka.academy.dto.topic.TopicItemDto;
import by.malanka.academy.entity.CourseEntity;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final CourseRepository courseRepository;

    public PageWrapper<CoursePreviewDto> getCourses(Pageable pageable) {
        List<CoursePreviewDto> courses = courseRepository.findAll(pageable).stream()
                .map(courseEntity -> new CoursePreviewDto(
                        courseEntity.getId().toString(),
                        courseEntity.getTitle(),
                        courseEntity.getDescription()))
                .toList();
        return new PageWrapper<>(courses);
    }

    public CourseDetailsDto getCourseDetails(String id) {
        CourseEntity courseEntity = courseRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException(UUID.fromString(id)));
        return new CourseDetailsDto(
                courseEntity.getId().toString(),
                courseEntity.getTitle(),
                courseEntity.getDescription(),
                courseEntity.getTopics().stream()
                        .map(topicEntity -> new TopicDto(
                                topicEntity.getId().toString(),
                                topicEntity.getTitle(),
                                topicEntity.getItems().stream()
                                        .map(item -> new TopicItemDto(
                                                item.getId().toString(),
                                                item.getTitle()))
                                        .toList()))
                        .toList()
        );
    }

}
