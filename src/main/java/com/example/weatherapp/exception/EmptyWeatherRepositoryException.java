package com.example.weatherapp.exception;

public class EmptyWeatherRepositoryException extends RuntimeException {
    public EmptyWeatherRepositoryException() {
        super("The repository is empty");
    }
}
