package by.malanka.academy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "courses")
@EqualsAndHashCode(callSuper = false, of = "title")
public class CourseEntity extends AuditEntity<UUID> {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @OrderBy("orderId")
    @OneToMany(mappedBy = "course")
    private List<TopicEntity> topics = new ArrayList<>();

}
