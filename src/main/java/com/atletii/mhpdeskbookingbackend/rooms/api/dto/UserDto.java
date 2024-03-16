package com.atletii.mhpdeskbookingbackend.rooms.api.dto;

import com.atletii.mhpdeskbookingbackend.common.api.BaseEntityDto;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseEntityDto {
    private String firstName, lastName, email, firebaseId;
    private UserRole userRole;
}
