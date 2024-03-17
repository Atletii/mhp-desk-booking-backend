package com.atletii.mhpdeskbookingbackend.rooms.api;

import com.atletii.mhpdeskbookingbackend.common.api.BaseResource;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.UserDto;
import com.atletii.mhpdeskbookingbackend.rooms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseResource {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Void> signUp(@RequestBody UserDto newUserDto){
        if(userService.existsEmail(newUserDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.signUp(newUserDto.getFirstName(), newUserDto.getLastName(), newUserDto.getEmail(), newUserDto.getFirebaseId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
