package by.malanka.academy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "resources")
@PrimaryKeyJoinColumn(name = "item_id")
@EqualsAndHashCode(callSuper = false, of = "content")
public class ResourceEntity extends TopicItemEntity {

    @Column(name = "content")
    private String content;

}
