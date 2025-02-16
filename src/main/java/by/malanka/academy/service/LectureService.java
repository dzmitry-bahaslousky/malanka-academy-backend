package by.malanka.academy.service;

import by.malanka.academy.dto.lecture.LectureDto;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureDto getLectureById(String id) {
        return lectureRepository.findById(UUID.fromString(id))
                .map(entity -> new LectureDto(
                        entity.getId().toString(),
                        entity.getTitle(),
                        entity.getVideoId())
                )
                .orElseThrow(() -> new ResourceNotFoundException(UUID.fromString(id)));
    }

}
