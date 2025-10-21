package com.doodle.minidoodle.service;

import com.doodle.minidoodle.model.entities.CalendarEntity;
import com.doodle.minidoodle.model.entities.SlotEntity;
import com.doodle.minidoodle.model.enums.SlotStatus;
import com.doodle.minidoodle.repository.TimeSlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TimeSlotService {

    private final TimeSlotRepository SlotEntityRepository;

    public SlotEntity createSlot(CalendarEntity calendar, LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }

        SlotEntity slot = SlotEntity.builder()
                .calendar(calendar)
                .startTime(start)
                .endTime(end)
                .status(SlotStatus.FREE)
                .build();

        return SlotEntityRepository.save(slot);
    }

    public void deleteSlot(Long id) {
        SlotEntityRepository.deleteById(id);
    }

    public List<SlotEntity> getSlots(CalendarEntity calendar) {
        return SlotEntityRepository.findByCalendar(calendar);
    }

    public List<SlotEntity> findSlotsByRange(CalendarEntity calendar, LocalDateTime start, LocalDateTime end) {
        return SlotEntityRepository.findByCalendarAndTimeRange(calendar, start, end);
    }

    public void markAsBusy(SlotEntity slot) {
        slot.setStatus(SlotStatus.BUSY);
        SlotEntityRepository.save(slot);
    }

    public void markAsFree(SlotEntity slot) {
        slot.setStatus(SlotStatus.FREE);
        SlotEntityRepository.save(slot);
    }

    public SlotEntity getSlotById(Long id) {
        return SlotEntityRepository.findById(id).orElse(null);
    }
}
