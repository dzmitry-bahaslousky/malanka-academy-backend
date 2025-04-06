package by.malanka.academy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseEntity extends AuditEntity {
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    
    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "duration")
    private String duration;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "rating", precision = 1, scale = 2)
    private BigDecimal rating;

    @Column(name = "enrolled_students")
    private Integer enrolledStudents;

    @Column(name = "img")
    private String img;

    @OneToMany(mappedBy = "course")
    private Set<TopicEntity> topics = new LinkedHashSet<>();

}