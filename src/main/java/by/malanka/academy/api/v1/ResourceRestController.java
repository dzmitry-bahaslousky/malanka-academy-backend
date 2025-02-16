package by.malanka.academy.api.v1;

import by.malanka.academy.dto.resource.ResourceDto;
import by.malanka.academy.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resources")
public class ResourceRestController {
    private final ResourceService resourceService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResourceDto getResourceDetails(@PathVariable @UUID String id) {
        return resourceService.getResourceById(id);
    }

}
