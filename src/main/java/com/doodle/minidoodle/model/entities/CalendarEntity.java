package com.doodle.minidoodle.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "calendar")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SlotEntity> timeSlots;
}
