package by.malanka.academy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "topics")
@EqualsAndHashCode(callSuper = false, of = "title")
public class TopicEntity extends BaseEntity<UUID> {

    @Column(name = "title")
    private String title;

    @Column(name = "order_id")
    private Integer orderId;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CourseEntity course;

    @Builder.Default
    @OrderBy("orderId")
    @OneToMany(mappedBy = "topic")
    private List<TopicItemEntity> items = new ArrayList<>();

}
