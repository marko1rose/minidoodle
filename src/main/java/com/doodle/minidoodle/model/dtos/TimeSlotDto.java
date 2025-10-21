package com.doodle.minidoodle.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotDto {
    private Long id;
    private Long calendarId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
}
