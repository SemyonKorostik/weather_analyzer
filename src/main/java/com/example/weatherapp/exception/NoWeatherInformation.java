package com.example.weatherapp.exception;

public class NoWeatherInformation extends RuntimeException {
    public NoWeatherInformation(String date) {
        super("There is no information about the weather for " + date);
    }
}
