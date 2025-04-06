package by.malanka.academy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resources")
@PrimaryKeyJoinColumn(name = "item_id")
@EqualsAndHashCode(callSuper = false, of = "content")
public class ResourceEntity extends TopicItemEntity {

    @Column(name = "content", nullable = false)
    private String content;

}