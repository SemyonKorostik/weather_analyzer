package com.example.weatherapp.controller;

import com.example.weatherapp.exception.InvalidDateRange;
import com.example.weatherapp.exception.NoWeatherInformation;
import com.example.weatherapp.handler.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(InvalidDateRange.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError invalidDateRange(InvalidDateRange exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError dateTimeParseException(DateTimeParseException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(NoWeatherInformation.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError noWeatherInformation(NoWeatherInformation exception) {
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
