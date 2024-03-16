package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import com.atletii.mhpdeskbookingbackend.common.api.BaseEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomDto extends BaseEntityDto {
    private String name;
    private Integer mapId;
    private Integer nrPlaces;
}
