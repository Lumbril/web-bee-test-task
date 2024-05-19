package ru.webbee.task.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.webbee.task.dto.responses.IsWorkingDayResponse;
import ru.webbee.task.exceptions.IncorrectYearException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IsWorkingDayServiceTest {
    @InjectMocks
    private IsWorkingDayService isWorkingDayService;

    @Test
    public void checkDayOffDateTest() {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDate(
                LocalDate.of(2024, 05, 11)
        );

        assertEquals(true, isWorkingDayResponse.getIsWeekend());
    }

    @Test
    public void checkWorkingSaturdayTest() {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDate(
                LocalDate.of(2024, 12, 28)
        );

        assertEquals(false, isWorkingDayResponse.getIsWeekend());
    }

    @Test
    public void checkHolidayTest() {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDate(
                LocalDate.of(2024, 1, 1)
        );

        assertEquals(true, isWorkingDayResponse.getIsWeekend());
    }

    @Test
    public void incorrectYearTest() {
        IncorrectYearException ex = assertThrows(IncorrectYearException.class,
                () -> isWorkingDayService.checkDate(
                        LocalDate.of(2023, 1, 1)
                ));

        assertEquals("Неверный год. Проверка возможна только для 2024", ex.getMessage());
    }

    @Test
    public void checkWeekendTimeTest() {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDateTime(
                LocalDateTime.of(2024, 5, 13, 19, 00)
        );

        assertEquals(true, isWorkingDayResponse.getIsWeekend());
    }

    @Test
    public void checkNotWeekendTimeTest() {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDateTime(
                LocalDateTime.of(2024, 5, 13, 14, 00)
        );

        assertEquals(false, isWorkingDayResponse.getIsWeekend());
    }
}
