package by.malanka.academy.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity<T extends Serializable> extends BaseEntity<T> {

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private UUID createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @LastModifiedBy
    private UUID lastModifiedBy;

}
