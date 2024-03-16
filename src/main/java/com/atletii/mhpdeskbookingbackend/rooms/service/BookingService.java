package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.UserMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.BookingRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.BookingEventType;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService extends BaseEntityService<Booking, BookingEntity> {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;
    private final BookingEventService bookingEventService;

    public List<Booking> getBookingByDay(LocalDate day){

        List<BookingEntity> bookingsFromOneDay= bookingRepository.findAllByBookedFromAfterAndBookedToBefore(day.atStartOfDay(), day.atTime(23,59,59));
        return bookingsFromOneDay.stream().map(bookingMapper::mapToModel).collect(Collectors.toList());
    }
    public Page<Booking> getBookingsOfUser(User user, Pageable pageable){
        Page<BookingEntity> allBooks = bookingRepository.findBookingEntitiesByUser(userMapper.mapToEntity(user), pageable);
        return bookingMapper.mapToModel(allBooks);
    }

    public boolean checkIfRoomIsAvailable(RoomEntity room,LocalDateTime bookedFrom, LocalDateTime bookedTo){
        List<BookingEntity> allRoomBookings = bookingRepository.findAllByByRoomAndTimeRange(room, bookedFrom, bookedTo);
        if(allRoomBookings.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public Booking createBooking(Room roomToBook, User user, LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        Booking booking = new Booking(roomToBook, user, bookedFrom, bookedTo);

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
