package com.doodle.minidoodle.repository;

import com.doodle.minidoodle.model.entities.CalendarEntity;
import com.doodle.minidoodle.model.entities.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<SlotEntity, Long> {

    List<SlotEntity> findByCalendar(CalendarEntity calendar);

    @Query("SELECT t FROM SlotEntity t WHERE t.calendar = :calendar " +
            "AND t.startTime >= :start AND t.endTime <= :end")
    List<SlotEntity> findByCalendarAndTimeRange(CalendarEntity calendar,
                                              LocalDateTime start,
                                              LocalDateTime end);
}
