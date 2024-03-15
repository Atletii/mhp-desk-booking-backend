package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailability;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.BookingRepository;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.RoomRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService extends BaseEntityService<Room, RoomEntity> {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    private final BookingRepository bookingRepository;

    public RoomAvailability getRoomAvailability(LocalDate day){
        List<BookingEntity> bookingsFromDay = bookingRepository.findAllByBookedFromAfterAndBookedToBefore( day.atStartOfDay(), day.atTime(23, 59, 59));
        if(bookingsFromDay.isEmpty()){
            return RoomAvailability.FREE;
        }
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(20, 0);

        boolean isFullyBooked = true;

        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            boolean slotCovered = false;
            for (BookingEntity booking : bookingsFromDay) {
                if (!booking.getBookedFrom().toLocalTime().isAfter(time) && !booking.getBookedTo().toLocalTime().isBefore(time)) {
                    slotCovered = true;
                    break;
                }
            }
            if (!slotCovered) {
                isFullyBooked = false;
                break;
            }
        }
        return isFullyBooked ? RoomAvailability.FULLY_BLOCKED : RoomAvailability.PARTLY_BLOCKED;
    }

    @Override
    protected RoomRepository getRepository() {
        return roomRepository;
    }

    @Override
    protected RoomMapper getMapper() {
        return roomMapper;
    }
}
