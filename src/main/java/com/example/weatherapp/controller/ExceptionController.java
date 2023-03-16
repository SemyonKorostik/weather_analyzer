package com.example.weatherapp.controller;

import com.example.weatherapp.exception.InvalidDateRange;
import com.example.weatherapp.exception.WeatherAPIRequestException;
import com.example.weatherapp.handler.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(InvalidDateRange.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError invalidDateRange(InvalidDateRange exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(WeatherAPIRequestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError weatherAPIRequestException(WeatherAPIRequestException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError constraintViolationException(ConstraintViolationException exception) {
        Map<String, String> violations = new HashMap<>();
        exception.getConstraintViolations()
                .forEach(error -> violations.put(error.getPropertyPath().toString(), error.getMessage()));
        return new ApiError(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> violations = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> violations.put(error.getField(), error.getDefaultMessage()));
        return new ApiError(violations);
    }
}
