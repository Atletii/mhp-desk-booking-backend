package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class NewBookingDto {
    private UUID roomId;
    private LocalDateTime bookedTo, bookedFrom;
}
