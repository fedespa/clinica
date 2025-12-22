package com.example.Clinica.common.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class FullAuditableEntity extends TraceableEntity {
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    protected Long updatedBy;

    protected LocalDateTime deletedAt;
}
