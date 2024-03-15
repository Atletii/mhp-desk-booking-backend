package com.atletii.mhpdeskbookingbackend.common.persistence;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Version
    private long version;

    @Column(name = "created", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private ZonedDateTime created;

    @Column(name = "updated", columnDefinition = "TIMESTAMP", nullable = false)
    private ZonedDateTime updated;

    @PrePersist
    protected void onPrePersist() {
        ZonedDateTime now = ZonedDateTime.now();
        created = now;
        updated = now;
    }

    @PreUpdate
    protected void onPreUpdate() {
        updated = ZonedDateTime.now();
    }
}
