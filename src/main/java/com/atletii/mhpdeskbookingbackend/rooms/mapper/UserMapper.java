package com.atletii.mhpdeskbookingbackend.rooms.mapper;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.UserEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseModelEntityMapper<User, UserEntity> {

}
