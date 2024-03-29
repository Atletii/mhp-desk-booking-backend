package com.atletii.mhpdeskbookingbackend.rooms.mapper;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper extends BaseModelEntityMapper<Booking, BookingEntity> {

    @Mapping(target = "room", source = "room.name")
    BookingDto toDto(Booking model);
    default List<BookingDto> mapToDto(List<Booking> page) {
        return page.stream().map(this::toDto).toList();
    }
}
