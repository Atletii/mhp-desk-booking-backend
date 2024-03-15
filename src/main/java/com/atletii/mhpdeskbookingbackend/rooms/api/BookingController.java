package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController extends BaseResource {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    @GetMapping("/byDay")
    public ResponseEntity<List<BookingDto>> getBookingsByDay(@RequestBody LocalDate day){
        bookingService.

    }
}
