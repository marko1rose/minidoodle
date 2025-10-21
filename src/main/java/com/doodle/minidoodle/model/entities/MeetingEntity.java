package com.doodle.minidoodle.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "meeting")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "time_slot_id", nullable = false, unique = true)
    private SlotEntity timeSlot;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    // Many participants can attend one meeting
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantEntity> participants;
}
