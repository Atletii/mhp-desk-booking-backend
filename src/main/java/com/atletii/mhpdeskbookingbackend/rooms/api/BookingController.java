package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.NewBookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.service.BookingEventService;
import com.atletii.mhpdeskbookingbackend.rooms.service.BookingService;
import com.atletii.mhpdeskbookingbackend.rooms.service.RoomService;
import com.atletii.mhpdeskbookingbackend.rooms.service.UserService;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.BookingEventType;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.security.InvalidParameterException;
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
    private final RoomService roomService;
    private final BookingMapper bookingMapper;
    private final UserService userService;
    private final BookingEventService bookingEventService;


    @GetMapping("/{day}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookingDto>> getBookingsByDay(@PathVariable LocalDate day) {
        List<Booking> bookingsFromOneDay = bookingService.getBookingByDay(day);
        return ResponseEntity.ok()
                .body(bookingsFromOneDay.stream().map(bookingMapper::toDto).collect(Collectors.toList()));
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<BookingDto>> getAllBookingsOfUser(@RequestHeader(name = "localId") String token) {
        Optional<User> optionalUser = userService.findUserEntityByFirebaseId(token);
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            List<Booking> allBookings = bookingService.getBookingsOfUser(optionalUser.get());
            return ResponseEntity.ok()
                    .body(bookingMapper.mapToDto(allBookings));
        }
    }

    @DeleteMapping("/id/{bookingId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID bookingId) {
        Optional<Booking> optionalBooking = bookingService.findById(bookingId);
        if (optionalBooking.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            bookingEventService.sendBundleEvent(optionalBooking.get(), BookingEventType.CANCELLATION);
            bookingService.delete(optionalBooking.get());
        }
        return ResponseEntity.ok().body("Deleted successfully");
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BookingDto> createBooking(@RequestBody NewBookingDto newBookingDto, @RequestHeader(name = "localId") String token) {
        Optional<Room> optionalRoom = roomService.findById(newBookingDto.getRoomId()); //check if room exists & available
        Optional<User> optionalUser = userService.findUserEntityByFirebaseId(token);



        if (optionalRoom.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!bookingService.checkIfRoomIsAvailable(optionalRoom.get(),newBookingDto.getBookedFrom(),newBookingDto.getBookedTo())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            bookingService.checkIfDateIsCorrect(newBookingDto.getBookedFrom(),newBookingDto.getBookedTo());
        }catch (InvalidParameterException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Booking booking = bookingService.createBooking(optionalRoom.get(), optionalUser.get(), newBookingDto.getBookedFrom(), newBookingDto.getBookedTo());
            return ResponseEntity.ok().body(bookingMapper.toDto(booking));
        }
    }

    @GetMapping(path = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getBundleEventStream() {
        SseEmitter emitter = new SseEmitter(-1L);
        bookingEventService.subscribe(emitter);
        return emitter;
    }
}
