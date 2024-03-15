package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import com.atletii.mhpdeskbookingbackend.common.api.BaseEntityDto;

import java.time.LocalDateTime;

public class BookingDto extends BaseEntityDto {
    private RoomDto room;
    private LocalDateTime bookedTo, bookedFrom;
}
