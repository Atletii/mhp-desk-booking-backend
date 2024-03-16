package com.atletii.mhpdeskbookingbackend.rooms.persistance.repository;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseRepository;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends BaseRepository<BookingEntity> {
     List<BookingEntity> findAllByBookedFrom(LocalDateTime bookedFrom);
     List<BookingEntity> findAllByBookedFromAfterAndBookedToBefore(LocalDateTime bookedFrom, LocalDateTime bookedTo);
     Page<BookingEntity> findBookingEntitiesByUser(UserEntity user, Pageable pageable);
}
