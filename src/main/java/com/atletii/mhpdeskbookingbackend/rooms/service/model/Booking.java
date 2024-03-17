package com.atletii.mhpdeskbookingbackend.rooms.service.model;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class Booking extends BaseEntityModel {
    private Room room;

    private User user;

    private LocalDateTime bookedTo, bookedFrom;

    private Integer members;

}
