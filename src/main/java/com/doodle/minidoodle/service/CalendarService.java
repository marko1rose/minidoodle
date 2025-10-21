package com.doodle.minidoodle.service;

import com.doodle.minidoodle.model.entities.CalendarEntity;
import com.doodle.minidoodle.model.entities.UserEntity;
import com.doodle.minidoodle.repository.CalendarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarEntity createCalendarForUser(UserEntity user) {
        CalendarEntity calendar = CalendarEntity.builder()
                .user(user)
                .build();
        return calendarRepository.save(calendar);
    }

    public CalendarEntity getCalendar(Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calendar not found"));
    }
}
