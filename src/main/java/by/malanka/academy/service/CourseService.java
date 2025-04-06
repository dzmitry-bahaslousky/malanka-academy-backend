package by.malanka.academy.service;

import by.malanka.academy.dto.PageWrapper;
import by.malanka.academy.dto.course.CourseDetailsDto;
import by.malanka.academy.dto.course.CoursePreviewDto;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.mapper.CourseMapper;
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
    private final CourseMapper courseMapper;

    public PageWrapper<CoursePreviewDto> getCourses(Pageable pageable) {
        List<CoursePreviewDto> courses = courseRepository.findAll(pageable).stream()
                .map(courseMapper::toPreviewDto)
                .toList();
        return new PageWrapper<>(courses);
    }

    public CourseDetailsDto getCourseDetails(String id) {
        return courseRepository.findById(UUID.fromString(id))
                .map(courseMapper::toDetailsDto)
                .orElseThrow(() -> new ResourceNotFoundException(UUID.fromString(id)));

    }

}
