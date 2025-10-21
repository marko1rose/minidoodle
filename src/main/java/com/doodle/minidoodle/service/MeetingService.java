package com.doodle.minidoodle.service;

import com.doodle.minidoodle.model.entities.MeetingEntity;
import com.doodle.minidoodle.model.entities.ParticipantEntity;
import com.doodle.minidoodle.model.entities.SlotEntity;
import com.doodle.minidoodle.model.entities.UserEntity;
import com.doodle.minidoodle.model.enums.SlotStatus;
import com.doodle.minidoodle.repository.MeetingRepository;
import com.doodle.minidoodle.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final ParticipantRepository participantRepository;
    private final TimeSlotService timeSlotService;

    public MeetingEntity scheduleMeeting(SlotEntity slot, String title, String description, List<UserEntity> participants) {
        if (slot.getStatus() == SlotStatus.BUSY) {
            throw new IllegalStateException("Slot already booked");
        }

        // Convert slot to busy
        timeSlotService.markAsBusy(slot);

        MeetingEntity meeting = MeetingEntity.builder()
                .timeSlot(slot)
                .title(title)
                .description(description)
                .build();

        meeting = meetingRepository.save(meeting);

        // Add participants
        for (UserEntity user : participants) {
            ParticipantEntity p = ParticipantEntity.builder()
                    .meeting(meeting)
                    .user(user)
                    .build();
            participantRepository.save(p);
        }

        return meeting;
    }
}
