package com.atletii.mhpdeskbookingbackend.rooms.persistance.repository;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseRepository;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends BaseRepository<BookingEntity> {
     List<BookingEntity> findAllByBookedFrom(LocalDateTime bookedFrom);
     List<BookingEntity> findAllByBookedFromAfterAndBookedToBefore(LocalDateTime bookedFrom, LocalDateTime bookedTo);
}