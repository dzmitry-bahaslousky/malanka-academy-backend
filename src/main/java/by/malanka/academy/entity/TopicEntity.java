package by.malanka.academy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "topics")
public class TopicEntity extends AuditEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @OrderBy("orderId")
    @OneToMany(mappedBy = "topic")
    private Set<TopicItemEntity> topicItems = new LinkedHashSet<>();

}