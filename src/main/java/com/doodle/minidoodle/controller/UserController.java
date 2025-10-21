package com.doodle.minidoodle.controller;

import com.doodle.minidoodle.model.dtos.UserDto;
import com.doodle.minidoodle.model.entities.UserEntity;
import com.doodle.minidoodle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto dto) {
        UserEntity user = userService.createUser(dto.getName(), dto.getEmail());
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable String email) {
        UserEntity user = userService.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
