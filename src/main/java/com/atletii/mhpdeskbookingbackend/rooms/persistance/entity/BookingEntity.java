package com.atletii.mhpdeskbookingbackend.rooms.persistance.entity;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {
    @ManyToOne
    private RoomEntity room;

    @ManyToOne
    private UserEntity user;

    private LocalDateTime bookedTo, bookedFrom;

    private Integer members;

}
