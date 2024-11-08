package by.malanka.academy.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(UUID id) {
        super("Failed to find resource with id " + id);
    }

}
