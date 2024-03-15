package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.BookingMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.BookingRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService extends BaseEntityService<Booking, BookingEntity> {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public List<BookingDto> getBookingByDay(LocalDate day){



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
