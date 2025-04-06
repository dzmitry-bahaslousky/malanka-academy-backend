package by.malanka.academy.dto.course;

import by.malanka.academy.dto.author.AuthorDto;

import java.math.BigDecimal;

public record CoursePreviewDto(
        String id,
        String title,
        String description,
        String level,
        String duration,
        BigDecimal price,
        BigDecimal rating,
        Integer enrolledStudents,
        String img,
        String category,
        AuthorDto author
) {
}
