package com.atletii.mhpdeskbookingbackend.rooms.persistance.repository;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseRepository;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends BaseRepository<BookingEntity> {
     List<BookingEntity> findAllByBookedFrom(LocalDateTime bookedFrom);
     List<BookingEntity> findAllByBookedFromAfterAndBookedToBefore(LocalDateTime bookedFrom, LocalDateTime bookedTo);
     List<BookingEntity> findBookingEntitiesByUser(UserEntity user);
     List<BookingEntity> findBookingEntitiesByRoom(RoomEntity room);

     //all Bookings from one room in a timeStamp
     @Query("SELECT b FROM BookingEntity b WHERE ((b.bookedFrom <= :to AND b.bookedTo >= :to) " +
             "OR   b.bookedTo >= :from) AND b.room = :room")
     List<BookingEntity> findAllByByRoomAndTimeRange(@Param("room") RoomEntity room, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
