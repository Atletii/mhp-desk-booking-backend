package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailability;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailabilityDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.RoomRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoomService extends BaseEntityService<Room, RoomEntity> {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    private final BookingService bookingService;

//    public RoomAvailability getRoomAvailability(LocalDate day){
//        List<BookingEntity> bookingsFromDay = bookingRepository.findAllByBookedFromAfterAndBookedToBefore( day.atStartOfDay(), day.atTime(23, 59, 59));
//        if(bookingsFromDay.isEmpty()){
//            return RoomAvailability.FREE;
//        }
//        LocalTime startTime = LocalTime.of(8, 0);
//        LocalTime endTime = LocalTime.of(20, 0);
//
//        boolean isFullyBooked = true;
//
//        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
//            boolean slotCovered = false;
//            for (BookingEntity booking : bookingsFromDay) {
//                if (!booking.getBookedFrom().toLocalTime().isAfter(time) && !booking.getBookedTo().toLocalTime().isBefore(time)) {
//                    slotCovered = true;
//                    break;
//                }
//            }
//            if (!slotCovered) {
//                isFullyBooked = false;
//                break;
//            }
//        }
//        return isFullyBooked ? RoomAvailability.FULLY_BLOCKED : RoomAvailability.PARTLY_BLOCKED;
//    }

    public List<RoomAvailabilityDto> getAllRoomsOnDay(LocalDate day) {
        List<Room> rooms = this.findAll();
        List<RoomAvailabilityDto> availabilities = new ArrayList<>();
        rooms.forEach(room -> {

            List<Booking> bookings = bookingService.getBookingByRoomAndDay(day, room);

            RoomAvailabilityDto availabilityDto = new RoomAvailabilityDto();
            availabilityDto.setId(room.getId());
            availabilityDto.setName(room.getName());
            availabilityDto.setMapId(room.getMapId());
            availabilityDto.setNrPlaces(room.getNrPlaces());
            if (bookings.isEmpty()) {
                availabilityDto.setAvailability(RoomAvailability.FREE);
            } else {
               Optional<Booking> a = bookings.stream().filter(b -> b.getBookedFrom().toLocalTime().equals(LocalTime.of(8, 0)) && b.getBookedTo().toLocalTime().equals(LocalTime.of(20,0))).findAny();
                availabilityDto.setAvailability(a.isEmpty()? RoomAvailability.PARTLY_BLOCKED: RoomAvailability.FULLY_BLOCKED );
            }
            availabilities.add(availabilityDto);
        });
        return availabilities;
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
