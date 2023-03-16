package com.example.weatherapp.exception;

public class WeatherAPIRequestException extends RuntimeException {
    public WeatherAPIRequestException() {
        super("Weather API request error");
    }
}
