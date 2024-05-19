package ru.webbee.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.webbee.task.dto.responses.ErrorResponse;
import ru.webbee.task.dto.responses.IsWorkingDayResponse;
import ru.webbee.task.exceptions.IncorrectYearException;
import ru.webbee.task.services.IsWorkingDayService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Tag(name = "Methods", description = "Методы из тестового задания")
@RestController
@RequiredArgsConstructor
public class IsWorkingDayController {
    private final IsWorkingDayService isWorkingDayService;

    @Operation(summary = "Первая реализация метода")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = IsWorkingDayResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    }
            )
    })
    @GetMapping("/method1")
    public ResponseEntity<?> method1(@Validated @RequestParam LocalDate date) {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDate(date);

        return ResponseEntity.ok().body(isWorkingDayResponse);
    }

    @Operation(summary = "Вторая реализация метода")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = IsWorkingDayResponse.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)
                            )
                    }
            )
    })
    @GetMapping("/method2")
    public ResponseEntity<?> method2(@Validated @RequestParam LocalDateTime dateTime) {
        IsWorkingDayResponse isWorkingDayResponse = isWorkingDayService.checkDateTime(dateTime);

        return ResponseEntity.ok().body(isWorkingDayResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handlerNotValidArgument(MethodArgumentTypeMismatchException e,
                                                     HttpServletRequest req) {
        String body = "Неверный формат данных. " +
                "Шаблон имеет вид: ";

        if (req.getRequestURI().equals("/method1")) {
            body += "yyyy-mm-dd";
        } else {
            body += "yyyy-mm-ddThh:MM:ss";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .message(body)
                        .build()
        );
    }

    @ExceptionHandler(IncorrectYearException.class)
    public  ResponseEntity<?> handlerIncorrectYear(IncorrectYearException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .build()
        );
    }
}
