package com.doodle.minidoodle.controller;

import com.doodle.minidoodle.model.dtos.CalendarDto;
import com.doodle.minidoodle.model.entities.CalendarEntity;
import com.doodle.minidoodle.model.entities.UserEntity;
import com.doodle.minidoodle.service.CalendarService;
import com.doodle.minidoodle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    private final UserService userService;

    @PostMapping("/{userEmail}")
    public CalendarDto createCalendar(@PathVariable String userEmail) {
        UserEntity user = userService.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        CalendarEntity calendar = calendarService.createCalendarForUser(user);

        return CalendarDto.builder()
                .id(calendar.getId())
                .userId(user.getId())
                .build();
    }
}
