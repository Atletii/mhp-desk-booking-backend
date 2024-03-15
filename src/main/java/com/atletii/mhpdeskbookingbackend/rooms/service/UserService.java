package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.common.persistence.BaseRepository;
import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.UserEntity;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;

public class UserService extends BaseEntityService<User, UserEntity> {
    @Override
    protected BaseRepository<UserEntity> getRepository() {
        return null;
    }

    @Override
    protected BaseModelEntityMapper<User, UserEntity> getMapper() {
        return null;
    }
}
