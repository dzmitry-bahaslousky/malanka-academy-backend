package by.malanka.academy.service;

import by.malanka.academy.dto.resource.ResourceDto;
import by.malanka.academy.exception.ResourceNotFoundException;
import by.malanka.academy.mapper.ResourceMapper;
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
    private final ResourceMapper resourceMapper;

    public ResourceDto getResourceById(String id) {
        return resourceRepository.findById(UUID.fromString(id))
                .map(resourceMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(UUID.fromString(id)));
    }

}
