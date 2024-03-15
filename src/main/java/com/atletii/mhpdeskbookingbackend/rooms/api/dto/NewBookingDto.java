package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
public class NewBookingDto {
    private UUID roomId;
    private ZonedDateTime bookedTo, bookedFrom;
}
