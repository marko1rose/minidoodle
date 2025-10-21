package com.doodle.minidoodle.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingDto {
    private Long id;
    private Long timeSlotId;
    private String title;
    private String description;
    private List<Long> participantIds;
}
