package by.malanka.academy.dto.topic;

import java.util.List;

public record TopicDto(String id, String title, List<TopicItemDto> items) {
}
