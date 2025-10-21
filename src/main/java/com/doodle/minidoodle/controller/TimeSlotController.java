package com.doodle.minidoodle.controller;

import com.doodle.minidoodle.model.dtos.TimeSlotDto;
import com.doodle.minidoodle.model.entities.CalendarEntity;
import com.doodle.minidoodle.model.entities.SlotEntity;
import com.doodle.minidoodle.service.CalendarService;
import com.doodle.minidoodle.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotService timeSlotService;
    private final CalendarService calendarService;

    @PostMapping
    public TimeSlotDto createSlot(@RequestParam Long calendarId,
                                  @RequestParam String start,
                                  @RequestParam String end) {
        CalendarEntity calendar = calendarService.getCalendar(calendarId);
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);

        SlotEntity slot = timeSlotService.createSlot(calendar, startTime, endTime);
        return mapToDto(slot);
    }

    @GetMapping("/{calendarId}")
    public List<TimeSlotDto> getSlots(@PathVariable Long calendarId) {
        CalendarEntity calendar = calendarService.getCalendar(calendarId);
        return timeSlotService.getSlots(calendar)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{slotId}")
    public void deleteSlot(@PathVariable Long slotId) {
        timeSlotService.deleteSlot(slotId);
    }

    private TimeSlotDto mapToDto(SlotEntity slot) {
        return TimeSlotDto.builder()
                .id(slot.getId())
                .calendarId(slot.getCalendar().getId())
                .startTime(slot.getStartTime())
                .endTime(slot.getEndTime())
                .status(slot.getStatus().name())
                .build();
    }
}
