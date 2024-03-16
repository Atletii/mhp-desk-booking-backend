package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.BookingEventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingEventDto {
    private Booking booking;
    private BookingEventType bookingEventType;
}
