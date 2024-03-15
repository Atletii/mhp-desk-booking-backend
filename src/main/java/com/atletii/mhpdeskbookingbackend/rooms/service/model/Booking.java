package com.atletii.mhpdeskbookingbackend.rooms.service.model;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityModel;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.UserEntity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@Setter
public class Booking extends BaseEntityModel {
    private Room room;

    private User user;

    private ZonedDateTime bookedTo, bookedFrom;
}
