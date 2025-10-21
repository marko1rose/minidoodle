package com.doodle.minidoodle.model.entities;

import com.doodle.minidoodle.model.enums.SlotStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "slot")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false)
    private CalendarEntity calendar;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus status = SlotStatus.FREE;

    @OneToOne(mappedBy = "timeSlot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MeetingEntity meeting;
}
