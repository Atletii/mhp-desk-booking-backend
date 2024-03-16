package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.NewBookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.RoomRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.BookingService;
import com.atletii.mhpdeskbookingbackend.rooms.service.UserService;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController extends BaseResource {

    private final BookingService bookingService;
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;
    private final UserService userService;


    @GetMapping("/byDay/{day}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookingDto>> getBookingsByDay(@PathVariable LocalDate day) {
        List<Booking> bookingsFromOneDay = bookingService.getBookingByDay(day);
        return ResponseEntity.ok()
                .body(bookingsFromOneDay.stream().map(bookingMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/allBookings")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookingDto>> getAllBookingsOfUser(@RequestHeader(name = "Authorization") String token) {
        Optional<User> optionalUser = userService.findUserEntityByFirebaseId(token);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            List<Booking> allBookings = bookingService.getBookingsOfUser(optionalUser.get());
            return ResponseEntity.ok()
                    .body(allBookings.stream().map(bookingMapper::toDto).collect(Collectors.toList()));
        }
    }

    @DeleteMapping("/deleteBooking/{bookingId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID bookingId) {
        Optional<Booking> optionalBooking = bookingService.findById(bookingId);
        if (optionalBooking.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            bookingService.delete(optionalBooking.get());
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PostMapping("/createBooking")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingDto> createBooking(@RequestBody NewBookingDto newBookingDto, @RequestHeader(name = "Authorization") String token) {
        Optional<RoomEntity> optionalRoom = roomRepository.findById(newBookingDto.getRoomId());
        Optional<User> optionalUser = userService.findUserEntityByFirebaseId(token);
        if (optionalRoom.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Booking booking = bookingService.createBooking(roomMapper.mapToModel(optionalRoom.get()), optionalUser.get(), newBookingDto.getBookedTo(), newBookingDto.getBookedTo());
            return ResponseEntity.ok().body(bookingMapper.toDto(booking));
        }
    }
}
