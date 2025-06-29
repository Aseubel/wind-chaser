package com.aseubel.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Aseubel
 * @date 2025/6/15 下午4:53
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @CreatedBy
    private Long createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime = LocalDateTime.now();

    @LastModifiedBy
    private Long modifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastModifiedTime = LocalDateTime.now();

    @Version
    private Long version;
    private Boolean deleted = false;

    @PreUpdate
    public void preUpdate() {
        System.out.println("preUpdate::" + this);
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("postUpdate::" + this);
    }

    @PreRemove
    public void preRemove() {
        System.out.println("preRemove::" + this);
    }

    @PostRemove
    public void postRemove() {
        System.out.println("postRemove::" + this);
    }

    @PostLoad
    public void postLoad() {
        System.out.println("postLoad::" + this);
    }
}
