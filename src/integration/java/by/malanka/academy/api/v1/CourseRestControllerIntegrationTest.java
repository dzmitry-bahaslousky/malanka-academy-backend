package by.malanka.academy.api.v1;

import by.malanka.academy.AbstractIntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@DisplayName("Course REST API Integration Tests")
public class CourseRestControllerIntegrationTest extends AbstractIntegrationTest {
    private final MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses method tests")
    class GetCoursesTests {

        @Test
        @DisplayName("get courses data")
        void testGetCourses() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/courses"))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$.items").isArray(),
                            jsonPath("$.items[0].id").isNotEmpty(),
                            jsonPath("$.items[0].title").isNotEmpty(),
                            jsonPath("$.items[0].description").isNotEmpty()
                    );
        }

    }

    @Nested
    @DisplayName("GET /courses/{id} method tests")
    class GetCoursesByIdTests {

        @Test
        @DisplayName("get courses data by id")
        void testGetCoursesById() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/courses/{id}", "3c91cc57-298a-4e88-9b40-a52d4720bb93"))
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("$.id").isNotEmpty(),
                            jsonPath("$.title").isNotEmpty(),
                            jsonPath("$.description").isNotEmpty(),
                            jsonPath("$.topics").isArray(),
                            jsonPath("$.topics[0].id").isNotEmpty(),
                            jsonPath("$.topics[0].title").isNotEmpty(),
                            jsonPath("$.topics[0].items").isArray(),
                            jsonPath("$.topics[0].items[0].id").isNotEmpty(),
                            jsonPath("$.topics[0].items[0].title").isNotEmpty()
                    );
        }

    }

}
