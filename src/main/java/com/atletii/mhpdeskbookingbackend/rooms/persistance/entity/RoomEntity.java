package com.atletii.mhpdeskbookingbackend.rooms.persistance.entity;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity {
    private String name;
    private Integer nrPlaces;
}
