package by.malanka.academy.api.v1;

import by.malanka.academy.dto.PageWrapper;
import by.malanka.academy.dto.course.CourseDetailsDto;
import by.malanka.academy.dto.course.CoursePreviewDto;
import by.malanka.academy.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseRestController {
    private final CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper<CoursePreviewDto> getCourses(@PageableDefault Pageable pageable) {
        return courseService.getCourses(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDetailsDto getCourseDetails(@PathVariable @UUID String id) {
        return courseService.getCourseDetails(id);
    }

}
