package com.doodle.minidoodle.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarDto {
    private Long id;
    private Long userId;
}
