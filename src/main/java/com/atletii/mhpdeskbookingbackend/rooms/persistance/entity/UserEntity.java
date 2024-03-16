package com.atletii.mhpdeskbookingbackend.rooms.persistance.entity;

import com.atletii.mhpdeskbookingbackend.common.persistence.BaseEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mhp_user")
public class UserEntity extends BaseEntity {
    private String firstName, lastName, email, firebaseId;
    private UserRole userRole;
}
