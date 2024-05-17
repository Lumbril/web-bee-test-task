package ru.webbee.task.controllers;

import io.swagger.v3.oas.annotations.Operation;
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

import java.time.LocalDate;

@Tag(name = "Methods", description = "Методы из тестового задания")
@RestController
@RequiredArgsConstructor
public class IsWorkingDayController {
    @Operation(summary = "Первая реализация метода")
    @ApiResponses(

    )
    @GetMapping("/method1")
    public ResponseEntity<?> method1(@Validated @RequestParam LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handlerNotValidArgument(MethodArgumentTypeMismatchException e,
                                                     HttpServletRequest req) {
        String body = "Неверный формат данных. " +
                "Шаблон имеет вид: ";

        if (req.getRequestURI().equals("/method1")) {
            body += "YYYY-MM-DD";
        } else {

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse.builder()
                        .message(body)
                        .build()
        );
    }
}
