package by.malanka.academy.repository;

import by.malanka.academy.entity.CourseEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = "topics")
    Optional<CourseEntity> findById(@NonNull UUID uuid);

}
