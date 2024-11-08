package by.malanka.academy.dto;

import java.util.List;

public record PageWrapper<T>(
        List<T> items
) {
}
