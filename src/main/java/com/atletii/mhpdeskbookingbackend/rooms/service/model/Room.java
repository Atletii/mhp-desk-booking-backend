package com.atletii.mhpdeskbookingbackend.rooms.service.model;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room extends BaseEntityModel {
    private String name;
    private Integer nrPlaces;
}
