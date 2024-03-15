package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.BookingRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService extends BaseEntityService<Booking, BookingEntity> {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;


    public List<Booking> getBookingByDay(LocalDate day){

        List<BookingEntity> bookingsFromOneDay= bookingRepository.findAllByBookedFromAfterAndBookedToBefore(day.atStartOfDay(), day.atTime(23,59,59));
        return bookingsFromOneDay.stream().map(bookingMapper::mapToModel).collect(Collectors.toList());
    }

    public Booking createBooking(Room roomToBook, User user, LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        Booking booking = new Booking(roomToBook, user, bookedFrom, bookedTo);
        return this.save(booking);
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
