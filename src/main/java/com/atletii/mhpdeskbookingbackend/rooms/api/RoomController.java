package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailability;
import com.atletii.mhpdeskbookingbackend.rooms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController extends BaseResource {
    private final RoomService roomService;

    @GetMapping("/available/{day}")
    public ResponseEntity<RoomAvailability> getRoomAvailabilityPerDay(LocalDate day){
        return new ResponseEntity<>(roomService.getRoomAvailability(day), HttpStatus.OK);
    }
}
