package by.malanka.academy.repository;

import by.malanka.academy.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @EntityGraph(attributePaths = "roles")
    Optional<UserEntity> findByUsername(String username);

}
