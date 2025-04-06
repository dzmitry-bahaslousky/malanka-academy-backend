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
@Table(name = "lectures")
@PrimaryKeyJoinColumn(name = "item_id")
@EqualsAndHashCode(callSuper = false, of = "videoId")
public class LectureEntity extends TopicItemEntity {

    @Column(name = "video_id")
    private String videoId;

}