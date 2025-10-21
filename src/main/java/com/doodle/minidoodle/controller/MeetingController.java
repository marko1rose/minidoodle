package com.doodle.minidoodle.controller;

import com.doodle.minidoodle.model.dtos.MeetingDto;
import com.doodle.minidoodle.model.entities.MeetingEntity;
import com.doodle.minidoodle.model.entities.SlotEntity;
import com.doodle.minidoodle.model.entities.UserEntity;
import com.doodle.minidoodle.service.MeetingService;
import com.doodle.minidoodle.service.TimeSlotService;
import com.doodle.minidoodle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;
    private final TimeSlotService timeSlotService;
    private final UserService userService;

    @PostMapping
    public MeetingDto createMeeting(@RequestBody MeetingDto dto) {
        SlotEntity slot = timeSlotService.getSlotById(dto.getTimeSlotId());
        if (slot == null) {
            throw new IllegalArgumentException("Time slot not found");
        }
        List<UserEntity> participants = dto.getParticipantIds().stream()
                .map(id -> userService.getUserById(id)
                        .orElseThrow(() -> new IllegalArgumentException("User not found: " + id)))
                .collect(Collectors.toList());

        MeetingEntity meeting = meetingService.scheduleMeeting(
                slot,
                dto.getTitle(),
                dto.getDescription(),
                participants
        );


        return MeetingDto.builder()
                .id(meeting.getId())
                .timeSlotId(meeting.getTimeSlot().getId())
                .title(meeting.getTitle())
                .description(meeting.getDescription())
                .participantIds(
                        meeting.getParticipants()
                                .stream()
                                .map(p -> p.getUser().getId())
                                .collect(Collectors.toList())
                )
                .build();
    }
}
