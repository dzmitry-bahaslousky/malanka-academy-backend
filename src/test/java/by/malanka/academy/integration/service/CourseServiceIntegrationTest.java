package by.malanka.academy.integration.service;

import by.malanka.academy.dto.PageWrapper;
import by.malanka.academy.dto.course.CourseDetailsDto;
import by.malanka.academy.dto.course.CoursePreviewDto;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.integration.AbstractIntegrationTest;
import by.malanka.academy.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RequiredArgsConstructor
@DisplayName("Course Service Integration Tests")
public class CourseServiceIntegrationTest extends AbstractIntegrationTest {
    private final CourseService courseService;

    @Nested
    @DisplayName("getCourses method tests")
    class GetCoursesTests {

        @Test
        @DisplayName("when get courses successfully")
        void whenGetCoursesSuccessfully() {
            PageWrapper<CoursePreviewDto> pageWrapper = courseService.getCourses(Pageable.ofSize(10));

            assertThat(pageWrapper).isNotNull();
            assertThat(pageWrapper.items()).isNotEmpty();
        }

    }

    @Nested
    @DisplayName("getCourseDetails method tests")
    class GetCourseDetailsTests {

        @Test
        @DisplayName("when course details exists")
        void whenCourseDetailsExists() {
            CourseDetailsDto courseDetails = courseService.getCourseDetails("3c91cc57-298a-4e88-9b40-a52d4720bb93");

            assertThat(courseDetails).isNotNull();
        }

        @Test
        @DisplayName("when course details doesn't exist")
        void whenCourseDetailsDoesNotExist() {
            assertThatThrownBy(() -> courseService.getCourseDetails("3c91cc57-298a-4e88-9b40-a52d4720bb95"))
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage("Failed to find resource with id 3c91cc57-298a-4e88-9b40-a52d4720bb95");
        }

    }

}
