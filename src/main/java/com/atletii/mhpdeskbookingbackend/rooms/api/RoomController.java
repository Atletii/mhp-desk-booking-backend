package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailability;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomAvailabilityDto;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.RoomDto;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.RoomMapper;
import com.atletii.mhpdeskbookingbackend.rooms.service.RoomService;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @GetMapping("/addAll")
    public void addRooms(){
        List<RoomDto> roomDtos = new ArrayList<>(List.of(
                new RoomDto("CLUJ_5_beta_1.1", 16, 1),
                new RoomDto("CLUJ_5_beta_1.2", 67, 1),
                new RoomDto("CLUJ_5_beta_1.3", 124, 1),
                new RoomDto("CLUJ_5_beta_1.4", 187, 1),
                new RoomDto("CLUJ_5_beta_2.1", 157, 1),
                new RoomDto("CLUJ_5_beta_2.2", 376, 1),
                new RoomDto("CLUJ_5_beta_2.3", 253, 1),
                new RoomDto("CLUJ_5_beta_2.4", 223, 1),
                new RoomDto("CLUJ_5_beta_3.1", 313, 1),
                new RoomDto("CLUJ_5_beta_3.2", 7, 1),
                new RoomDto("CLUJ_5_beta_3.3", 76, 1),
                new RoomDto("CLUJ_5_beta_3.4", 118, 1),
                new RoomDto("CLUJ_5_beta_4.1", 166, 1),
                new RoomDto("CLUJ_5_beta_4.2", 406, 1),
                new RoomDto("CLUJ_5_beta_4.3", 358, 1),
                new RoomDto("CLUJ_5_beta_4.4", 247, 1),
                new RoomDto("CLUJ_5_beta_5.1", 229, 1),
                new RoomDto("CLUJ_5_beta_5.2", 316, 1),
                new RoomDto("CLUJ_5_beta_5.3", 10, 1),
                new RoomDto("CLUJ_5_beta_5.4", 70, 1),
                new RoomDto("CLUJ_5_beta_6.1", 121, 1),
                new RoomDto("CLUJ_5_beta_6.2", 163, 1),
                new RoomDto("CLUJ_5_beta_6.3", 403, 1),
                new RoomDto("CLUJ_5_beta_6.4", 364, 1),
                new RoomDto("CLUJ_5_beta_7.1", 283, 1),
                new RoomDto("CLUJ_5_beta_7.2", 232, 1),
                new RoomDto("CLUJ_5_beta_7.3", 310, 1),
                new RoomDto("CLUJ_5_beta_7.4", 19, 1),
                new RoomDto("CLUJ_5_beta_8.1", 73, 1),
                new RoomDto("CLUJ_5_beta_8.2", 127, 1),
                new RoomDto("CLUJ_5_beta_8.3", 172, 1),
                new RoomDto("CLUJ_5_beta_8.4", 409, 1),
                new RoomDto("CLUJ_5_beta_9.1", 361, 1),
                new RoomDto("CLUJ_5_beta_9.2", 289, 1),
                new RoomDto("CLUJ_5_beta_9.3", 262, 1),
                new RoomDto("CLUJ_5_beta_9.4", 319, 1),
                new RoomDto("CLUJ_5_beta_10.1", 28, 1),
                new RoomDto("CLUJ_5_beta_10.2", 79, 1),
                new RoomDto("CLUJ_5_beta_10.3", 205, 1),
                new RoomDto("CLUJ_5_beta_10.4", 169, 1),
                new RoomDto("CLUJ_5_beta_11.1", 412, 1),
                new RoomDto("CLUJ_5_beta_11.2", 355, 1),
                new RoomDto("CLUJ_5_beta_11.3", 292, 1),
                new RoomDto("CLUJ_5_beta_11.4", 268, 1),
                new RoomDto("CLUJ_5_beta_12.1", 325, 1),
                new RoomDto("CLUJ_5_beta_12.2", 22, 1),
                new RoomDto("CLUJ_5_beta_12.3", 88, 1),
                new RoomDto("CLUJ_5_beta_12.4", 208, 1),
                new RoomDto("CLUJ_5_beta_13.1", 160, 1),
                new RoomDto("CLUJ_5_beta_13.2", 400, 1),
                new RoomDto("CLUJ_5_beta_13.3", 349, 1),
                new RoomDto("CLUJ_5_beta_13.4", 286, 1),
                new RoomDto("CLUJ_5_beta_14.1", 265, 1),
                new RoomDto("CLUJ_5_beta_14.2", 328, 1),
                new RoomDto("CLUJ_5_beta_14.3", 25, 1),
                new RoomDto("CLUJ_5_beta_14.4", 82, 1),
                new RoomDto("CLUJ_5_beta_15.1", 202, 1),
                new RoomDto("CLUJ_5_beta_15.2", 178, 1),
                new RoomDto("CLUJ_5_beta_15.3", 391, 1),
                new RoomDto("CLUJ_5_beta_15.4", 352, 1),
                new RoomDto("CLUJ_5_beta_16.1", 274, 1),
                new RoomDto("CLUJ_5_beta_16.2", 259, 1),
                new RoomDto("CLUJ_5_beta_16.3", 322, 1),
                new RoomDto("CLUJ_5_beta_16.4", 31, 1),
                new RoomDto("CLUJ_5_beta_17.1", 85, 1),
                new RoomDto("CLUJ_5_beta_17.2", 199, 1),
                new RoomDto("CLUJ_5_beta_17.3", 184, 1),
                new RoomDto("CLUJ_5_beta_17.4", 394, 1),
                new RoomDto("CLUJ_5_beta_18.1", 346, 1),
                new RoomDto("CLUJ_5_beta_18.2", 280, 1),
                new RoomDto("CLUJ_5_beta_18.3", 214, 1),
                new RoomDto("CLUJ_5_beta_18.4", 304, 1),
                new RoomDto("CLUJ_5_beta_18.5", 40, 1),
                new RoomDto("CLUJ_5_beta_19.1", 91, 1),
                new RoomDto("CLUJ_5_beta_19.2", 139, 1),
                new RoomDto("CLUJ_5_beta_19.3", 181, 1),
                new RoomDto("CLUJ_5_beta_19.4", 382, 1),
                new RoomDto("CLUJ_5_beta_19.5", 343, 1),
                new RoomDto("CLUJ_5_beta_19.6", 277, 1),
                new RoomDto("CLUJ_5_beta_20.1", 220, 1),
                new RoomDto("CLUJ_5_beta_20.2", 397, 1),
                new RoomDto("CLUJ_5_beta_20.3", 34, 1),
                new RoomDto("CLUJ_5_beta_20.4", 100, 1),
                new RoomDto("CLUJ_5_beta_21.1", 136, 1),
                new RoomDto("CLUJ_5_beta_21.2", 175, 1),
                new RoomDto("CLUJ_5_beta_21.3", 388, 1),
                new RoomDto("CLUJ_5_beta_21.4", 334, 1),
                new RoomDto("CLUJ_5_beta_22.1", 271, 1),
                new RoomDto("CLUJ_5_beta_22.2", 217, 1),
                new RoomDto("CLUJ_5_beta_22.3", 37, 1),
                new RoomDto("CLUJ_5_beta_22.4", 94, 1),
                new RoomDto("CLUJ_5_beta_23.1", 130, 1),
                new RoomDto("CLUJ_5_beta_23.2", 148, 1),
                new RoomDto("CLUJ_5_beta_23.3", 385, 1),
                new RoomDto("CLUJ_5_beta_23.4", 340, 1),
                new RoomDto("CLUJ_5_beta_24.1", 241, 1),
                new RoomDto("CLUJ_5_beta_24.2", 211, 1),
                new RoomDto("CLUJ_5_beta_24.3", 43, 1),
                new RoomDto("CLUJ_5_beta_24.4", 97, 1),
                new RoomDto("CLUJ_5_beta_25.1", 133, 1),
                new RoomDto("CLUJ_5_beta_25.2", 151, 1),
                new RoomDto("CLUJ_5_beta_25.3", 379, 1),
                new RoomDto("CLUJ_5_beta_25.4", 337, 1),
                new RoomDto("CLUJ_5_beta_26.1", 235, 1),
                new RoomDto("CLUJ_5_beta_26.2", 295, 1),
                new RoomDto("CLUJ_5_beta_26.3", 52, 1),
                new RoomDto("CLUJ_5_beta_26.4", 103, 1),
                new RoomDto("CLUJ_5_beta_27.1", 190, 1),
                new RoomDto("CLUJ_5_beta_27.2", 145, 1),
                new RoomDto("CLUJ_5_beta_27.3", 370, 1),
                new RoomDto("CLUJ_5_beta_27.4", 331, 1),
                new RoomDto("CLUJ_5_beta_28.1", 238, 1),
                new RoomDto("CLUJ_5_beta_28.2", 301, 1),
                new RoomDto("CLUJ_5_beta_28.3", 46, 1),
                new RoomDto("CLUJ_5_beta_28.4", 112, 1),
                new RoomDto("CLUJ_5_beta_29.1", 196, 1),
                new RoomDto("CLUJ_5_beta_29.2", 142, 1),
                new RoomDto("CLUJ_5_beta_29.3", 367, 1),
                new RoomDto("CLUJ_5_beta_29.4", 250, 1),
                new RoomDto("CLUJ_5_beta_30.1", 244, 1),
                new RoomDto("CLUJ_5_beta_30.2", 298, 1),
                new RoomDto("CLUJ_5_beta_30.3", 49, 1),
                new RoomDto("CLUJ_5_beta_30.4", 106, 1),
                new RoomDto("CLUJ_5_beta_31.1", 193, 1),
                new RoomDto("CLUJ_5_beta_31.2", 154, 1),
                new RoomDto("CLUJ_5_beta_31.3", 373, 1),
                new RoomDto("CLUJ_5_beta_31.4", 256, 1),
                new RoomDto("CLUJ_5_beta_32.1", 226, 1),
                new RoomDto("CLUJ_5_beta_32.2", 307, 1),
                new RoomDto("CLUJ_5_beta_32.3", 55, 1),
                new RoomDto("CLUJ_5_beta_32.4", 109, 1),
                new RoomDto("CLUJ_5_beta_33.1", 64, 1),
                new RoomDto("CLUJ_5_beta_33.2", 115, 1),
                new RoomDto("CLUJ_5_beta_33.3", 58, 1),
                new RoomDto("CLUJ_5_beta_33.4", 61, 1)
        ));
        roomDtos.forEach(r->{roomService.save(new Room(r.getName(), r.getNrPlaces(), r.getMapId()));});
    }
}
