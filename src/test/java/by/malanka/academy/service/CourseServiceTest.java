package by.malanka.academy.service;

import by.malanka.academy.dto.PageWrapper;
import by.malanka.academy.dto.course.CourseDetailsDto;
import by.malanka.academy.dto.course.CoursePreviewDto;
import by.malanka.academy.entity.CourseEntity;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.repository.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Course Service Tests")
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Nested
    @DisplayName("getCourses method tests")
    class GetCoursesTests {

        @Test
        @DisplayName("when return several courses")
        void whenReturnCourses() {
            List<CourseEntity> courses = Stream.generate(CourseServiceTest::createCourseEntity)
                    .limit(5)
                    .toList();
            when(courseRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(courses));

            PageWrapper<CoursePreviewDto> pageWrapper = courseService.getCourses(mock(Pageable.class));

            assertThat(pageWrapper).isNotNull();
            assertThat(pageWrapper.items()).hasSize(5);
        }

        @Test
        @DisplayName("when return 0 courses")
        void whenReturnNoCourses() {
            when(courseRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

            PageWrapper<CoursePreviewDto> pageWrapper = courseService.getCourses(mock(Pageable.class));

            assertThat(pageWrapper).isNotNull();
            assertThat(pageWrapper.items()).isEmpty();
        }

    }

    @Nested
    @DisplayName("getCourseDetails method tests")
    class GetCourseDetailsTests {

        @Test
        @DisplayName("when return existing course")
        void whenReturnExistingCourse() {
            when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.of(createCourseEntity()));

            CourseDetailsDto courseDetails = courseService.getCourseDetails(UUID.randomUUID().toString());

            assertThat(courseDetails).isNotNull();
        }

        @Test
        @DisplayName("when course not found")
        void whenCourseNotFound() {
            when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

            assertThatThrownBy(() -> courseService.getCourseDetails(UUID.randomUUID().toString()))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessageContaining("Failed to find resource with id");
        }

    }

    private static CourseEntity createCourseEntity() {
        return CourseEntity.builder()
                .id(UUID.randomUUID())
                .title("title")
                .description("description")
                .topics(List.of())
                .build();
    }

}
