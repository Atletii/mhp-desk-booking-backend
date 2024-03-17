package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.UserMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.BookingRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.BookingEventType;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService extends BaseEntityService<Booking, BookingEntity> {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;
    private final BookingEventService bookingEventService;
    private final RoomMapper roomMapper;

    public List<Booking> getBookingByDay(LocalDate day){

        List<BookingEntity> bookingsFromOneDay= bookingRepository.findAllByBookedFromAfterAndBookedToBefore(day.atStartOfDay(), day.atTime(23,59,59));
        return bookingsFromOneDay.stream().map(bookingMapper::mapToModel).collect(Collectors.toList());
    }
    public List<Booking> getBookingByRoomAndDay(LocalDate day, Room room){
        List<Booking> bookings = bookingMapper.mapToModels(bookingRepository.findBookingEntitiesByRoom(roomMapper.mapToEntity(room)));
        return bookings.stream().filter(booking -> booking.getBookedFrom().toLocalDate().isEqual(day) || booking.getBookedTo().toLocalDate().isEqual(day)).toList();
    }

        public List<Booking> getBookingsOfUser(User user){
        List<BookingEntity> allBooks = bookingRepository.findBookingEntitiesByUser(userMapper.mapToEntity(user));
        return bookingMapper.mapToModels(allBooks);
    }

    public boolean checkIfRoomIsAvailable(Room room,LocalDateTime bookedFrom, LocalDateTime bookedTo){
        List<BookingEntity> allRoomBookings = bookingRepository.findAllByRoomAndTimeRange(roomMapper.mapToEntity(room), bookedFrom, bookedTo);
        return allRoomBookings.isEmpty();
    }
    public void checkIfDateIsCorrect(LocalDateTime bookedFrom, LocalDateTime bookedTo){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime bookingStartTime = LocalTime.of(7, 59); // Booking can only start from 8 am

        if(!(bookedFrom.toLocalTime().isAfter(bookingStartTime)
                && bookedTo.isAfter(bookedFrom)))
            throw new InvalidParameterException("Invalid date!");
    }

    public Booking createBooking(Room roomToBook, User user, LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        Booking booking = new Booking(roomToBook, user, bookedTo, bookedFrom);

        Booking saved = this.save(booking);
        bookingEventService.sendBundleEvent(saved, BookingEventType.RESERVATION);
        return saved;
    }

    @Override
    protected BookingRepository getRepository() {
        return bookingRepository;
    }

    @Override
    protected BookingMapper getMapper() {
        return bookingMapper;
    }
}
