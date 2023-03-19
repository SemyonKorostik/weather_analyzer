package com.example.weatherapp.service.impl;

import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.exception.InvalidDateRangeException;
import com.example.weatherapp.service.WeatherValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherValidationServiceImpl implements WeatherValidationService {
    @Override
    public void validateDateRange(DateRange range) {
        if (range.getFrom().isAfter(range.getTo())) {
            throw new InvalidDateRangeException();
        }
    }
}
