package by.malanka.academy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@EqualsAndHashCode(callSuper = false, of = "title")
public class TopicItemEntity extends BaseEntity<UUID> {

    @Column(name = "title")
    private String title;

    @Column(name = "order_id")
    private Integer orderId;

    @JoinColumn(name = "topic_id")
    @ManyToOne(optional = false)
    private TopicEntity topic;

}
