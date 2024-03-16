package com.atletii.mhpdeskbookingbackend.rooms.service.model;

import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntityModel {
    private String firstName, lastName, email, firebaseId;
    private UserRole userRole;
}
