package ru.webbee.task.exceptions;

public class IncorrectYearException extends RuntimeException {
    public IncorrectYearException() {
        super("Неверный год. Проверка возможна только для 2024");
    }
}
