package by.malanka.academy.api.v1;

import by.malanka.academy.dto.lecture.LectureDto;
import by.malanka.academy.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lectures")
public class LectureRestController {
    private final LectureService lectureService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectureDto getLectureDetail(@PathVariable @UUID String id) {
        return lectureService.getLectureById(id);
    }

}
