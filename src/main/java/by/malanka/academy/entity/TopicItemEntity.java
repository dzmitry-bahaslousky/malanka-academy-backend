package by.malanka.academy.entity;

import by.malanka.academy.dto.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "topic_items")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = false, of = "title")
public class TopicItemEntity extends BaseEntity<UUID> {

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ItemTypeEnum type;

    @Column(name = "order_id")
    private Integer orderId;

    @JoinColumn(name = "topic_id")
    @ManyToOne(optional = false)
    private TopicEntity topic;

}
