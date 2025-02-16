package by.malanka.academy.repository;

import by.malanka.academy.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceRepository extends JpaRepository<ResourceEntity, UUID> {
}
