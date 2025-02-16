package by.malanka.academy.service;

import by.malanka.academy.dto.resource.ResourceDto;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceDto getResourceById(String id) {
        return resourceRepository.findById(UUID.fromString(id))
                .map(entity -> new ResourceDto(
                        entity.getId().toString(),
                        entity.getTitle(),
                        entity.getContent()
                ))
                .orElseThrow(() -> new ResourceNotFoundException(UUID.fromString(id)));
    }

}
