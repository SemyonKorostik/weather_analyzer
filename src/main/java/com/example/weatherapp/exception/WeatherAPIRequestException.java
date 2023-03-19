package com.example.weatherapp.exception;

public class WeatherAPIRequestException extends RuntimeException {
    public WeatherAPIRequestException(String message) {
        super(message);
    }
}
