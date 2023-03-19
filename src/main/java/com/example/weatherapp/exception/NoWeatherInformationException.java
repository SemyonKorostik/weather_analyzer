package com.example.weatherapp.exception;

public class NoWeatherInformationException extends RuntimeException {
    public NoWeatherInformationException(String date) {
        super("There is no information about the weather for " + date);
    }
}
