package by.malanka.academy.dto.course;

import by.malanka.academy.dto.author.AuthorDto;
import by.malanka.academy.dto.topic.TopicDto;

import java.math.BigDecimal;
import java.util.List;

public record CourseDetailsDto(
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
        AuthorDto author,
        List<TopicDto> topics) {
}
