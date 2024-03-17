package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import com.atletii.mhpdeskbookingbackend.common.api.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomAvailabilityDto extends BaseEntityDto{
    private String name;
    private Integer mapId, nrPlaces;
    private RoomAvailability availability;
}
