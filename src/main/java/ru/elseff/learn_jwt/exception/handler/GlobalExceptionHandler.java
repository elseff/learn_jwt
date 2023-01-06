package ru.elseff.learn_jwt.exception.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public void globalHandle(RuntimeException exception, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(ResponseStatusException.class)
    public void responseStatusExceptionHandle(ResponseStatusException exception, HttpServletResponse response) throws IOException {
        response.sendError(exception.getStatus().value(), exception.getReason());
    }
}
