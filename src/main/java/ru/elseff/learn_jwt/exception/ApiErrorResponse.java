package ru.elseff.learn_jwt.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse {
    private final String status;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH-mm-ss")
    private final LocalDateTime time = LocalDateTime.now();

    public ApiErrorResponse(HttpStatus status, String message) {
        this.status = status.getReasonPhrase();
        this.message = message;
    }
}
