package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.mapper.BaseModelEntityMapper;
import com.atletii.mhpdeskbookingbackend.common.service.BaseEntityService;
import com.atletii.mhpdeskbookingbackend.rooms.mapper.UserMapper;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.entity.UserEntity;
import com.atletii.mhpdeskbookingbackend.rooms.persistance.repository.UserRepository;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends BaseEntityService<User, UserEntity> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Boolean existsEmail(String email) {

        return userRepository.getUserEntityByEmail(email).isPresent();
    }

    public Optional<User> findUserEntityByFirebaseId(String id){
        return userMapper.mapToModel(userRepository.findUserEntityByFirebaseId(id));
    }

    public void signUp(String firstName, String lastName, String email, String firebaseId) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setFirebaseId(firebaseId);

        save(user);
    }

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }
}
