package ru.webbee.task.utils;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Holidays {
    private Set<LocalDate> holidays = new HashSet<>(List.of(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2),
            LocalDate.of(2024, 1, 3),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 5),
            LocalDate.of(2024, 1, 8),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 4, 29),
            LocalDate.of(2024, 4, 30),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 6, 12),
            LocalDate.of(2024, 11, 4),
            LocalDate.of(2024, 12, 30),
            LocalDate.of(2024, 12, 31)
    ));

    private Set<LocalDate> workingSaturday = new HashSet<>(List.of(
            LocalDate.of(2024, 4, 27),
            LocalDate.of(2024, 11, 2),
            LocalDate.of(2024, 12, 28)
    ));
}
