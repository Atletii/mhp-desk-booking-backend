package com.atletii.mhpdeskbookingbackend.rooms.mapper;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.RoomEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends BaseModelEntityMapper<Room, RoomEntity> {
}
