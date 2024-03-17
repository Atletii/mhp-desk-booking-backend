package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import com.atletii.mhpdeskbookingbackend.common.api.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDto extends BaseEntityDto {
    private String room;
    private LocalDateTime bookedTo, bookedFrom;
    private Integer members;
}
