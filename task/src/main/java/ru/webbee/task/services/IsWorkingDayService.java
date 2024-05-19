package ru.webbee.task.services;

import org.springframework.stereotype.Service;
import ru.webbee.task.dto.responses.IsWorkingDayResponse;
import ru.webbee.task.exceptions.IncorrectYearException;
import ru.webbee.task.utils.Holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class IsWorkingDayService {
    private Holidays holidays = new Holidays();
    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(18, 0);

    public IsWorkingDayResponse checkDate(LocalDate date) {
        if (date.getYear() != 2024) {
            throw new IncorrectYearException();
        }

        IsWorkingDayResponse workingDayResponse = new IsWorkingDayResponse();

        if (holidays.getWorkingSaturday().contains(date)) {
            workingDayResponse.setIsWeekend(false);
        } else if (holidays.getHolidays().contains(date)) {
            workingDayResponse.setIsWeekend(true);
        } else {
            DayOfWeek day = date.getDayOfWeek();
            workingDayResponse.setIsWeekend(
                    (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
            );
        }

        return  workingDayResponse;
    }

    public IsWorkingDayResponse checkDateTime(LocalDateTime dateTime) {
        IsWorkingDayResponse workingDayResponse = checkDate(dateTime.toLocalDate());

        if (!workingDayResponse.getIsWeekend()) {
            LocalTime localTime = dateTime.toLocalTime();
            workingDayResponse.setIsWeekend(
                    !(localTime.isAfter(START_TIME) && localTime.isBefore(END_TIME))
            );
        }

        return workingDayResponse;
    }
}
