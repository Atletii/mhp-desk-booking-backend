package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailability;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailabilityDto;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController extends BaseResource {
    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping("/available/{day}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RoomAvailability> getRoomAvailabilityPerDay(@PathVariable LocalDate day, @RequestHeader(name="Authorization") String token){
        return new ResponseEntity<>(roomService.getRoomAvailability(day), HttpStatus.OK);
    }

    @GetMapping("/allRooms")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RoomAvailabilityDto>> getAllRooms(@RequestHeader(name="Authorization") String token){
        return  ResponseEntity.ok().body(roomService
                .findAll()
                .stream()
                .map(roomMapper::toDto)
                .collect(Collectors.toList()));
    }
}
