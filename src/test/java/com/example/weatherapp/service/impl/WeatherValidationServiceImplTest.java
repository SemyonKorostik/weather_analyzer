package com.example.weatherapp.service.impl;

import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.exception.InvalidDateRangeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class WeatherValidationServiceImplTest {
    @InjectMocks
    private WeatherValidationServiceImpl weatherValidationService;

    @Test
    void validateDateRangeSuccess() {
        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now());
        Assertions.assertDoesNotThrow(() -> weatherValidationService.validateDateRange(dateRange));
    }

    @Test
    void validateDateRangeThenShouldThrowException() {
        DateRange dateRange = new DateRange(LocalDate.now(), LocalDate.now().minusDays(1));
        Assertions.assertThrows(InvalidDateRangeException.class,
                () -> weatherValidationService.validateDateRange(dateRange));
    }
}