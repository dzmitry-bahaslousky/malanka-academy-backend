package by.malanka.academy.dto.course;

import by.malanka.academy.dto.topic.TopicDto;

import java.util.List;

public record CourseDetailsDto(String id, String title, String description, List<TopicDto> topics) {
}
