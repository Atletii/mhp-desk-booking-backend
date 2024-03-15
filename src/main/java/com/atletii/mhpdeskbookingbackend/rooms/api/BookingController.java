package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.NewBookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.service.BookingService;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController extends BaseResource {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    @GetMapping("/byDay/{day}")
    public ResponseEntity<List<BookingDto>> getBookingsByDay(@PathVariable LocalDate day){
        List<Booking> bookingsFromOneDay = bookingService.getBookingByDay(day);

        return ResponseEntity.ok()
                .body(bookingsFromOneDay.stream().map(bookingMapper::toDto).collect(Collectors.toList()));
    }

    //TODO get all bookings
    @GetMapping("/allBookings")
    public ResponseEntity<List<BookingDto>> getAllBookings(){
        List<Booking> allBookings = bookingService.findAll();
        return ResponseEntity.ok()
                .body(allBookings.stream().map(bookingMapper::toDto).collect(Collectors.toList()));
    }

    //TODO delete booking by  id

    @DeleteMapping("/deleteBooking/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID bookingId){
        Optional<Booking> optionalBooking = bookingService.findById(bookingId);
        if(optionalBooking.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            bookingService.delete(optionalBooking.get());
        return ResponseEntity.ok().body("Deleted successfully");

    }

    //TODO create booking (@RB BookingDto)
//    @PostMapping("/createBooking")
//    public ResponseEntity<BookingDto> createBooking(@ResponseBody NewBookingDto newBookingDto){
//        Booking booking = bookingService.createBooking(newBookingDto.getRoomId(),newBookingDto.getBookedTo(),newBookingDto.getBookedTo());
//    }



}
