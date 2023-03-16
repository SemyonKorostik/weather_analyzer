package com.example.weatherapp.exception;

public class InvalidDateRange extends RuntimeException {
    public InvalidDateRange() {
        super("Invalid date range");
    }
}
