package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.service.BookingService;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController extends BaseResource {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    @GetMapping("/byDay")
    public ResponseEntity<List<BookingDto>> getBookingsByDay(@RequestBody ZonedDateTime day){
        List<Booking> bookingsFromOneDay = bookingService.getBookingByDay(day);

        return ResponseEntity.ok()
                .body(bookingsFromOneDay.stream().map(bookingMapper::toDto).collect(Collectors.toList()));
    }
}
