package com.atletii.mhpdeskbookingbackend.rooms.mapper;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper extends BaseModelEntityMapper<Booking, BookingEntity> {

    BookingDto toDto(Booking model);
}
