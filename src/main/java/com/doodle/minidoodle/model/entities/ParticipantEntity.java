package com.doodle.minidoodle.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participants",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"meeting_id", "user_id"})
        })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    private MeetingEntity meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
