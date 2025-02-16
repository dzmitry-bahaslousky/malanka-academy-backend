package by.malanka.academy.repository;

import by.malanka.academy.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LectureRepository extends JpaRepository<LectureEntity, UUID> {
}
