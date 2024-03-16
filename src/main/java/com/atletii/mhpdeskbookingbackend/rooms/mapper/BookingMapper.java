package com.atletii.mhpdeskbookingbackend.rooms.mapper;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingDto;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.BookingEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper extends BaseModelEntityMapper<Booking, BookingEntity> {

    BookingDto toDto(Booking model);
    default Page<BookingDto> mapToDto(Page<Booking> page) {
        List<BookingDto> content = page.stream().map(this::toDto).toList();
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
