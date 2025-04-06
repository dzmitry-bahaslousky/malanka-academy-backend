package by.malanka.academy.entity;

import by.malanka.academy.dto.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "topic_items")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = false, of = "title")
public class TopicItemEntity extends AuditEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ItemTypeEnum type;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

}