package com.example.weatherapp.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiError implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private Object message;

    private ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(Object message) {
        this();
        this.message = message;
    }

}
