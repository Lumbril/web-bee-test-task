package ru.webbee.task.dto.responses;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IsWorkingDayResponse {
    private Boolean isWeekend;
}
