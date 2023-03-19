package com.example.weatherapp.controller;

import com.example.weatherapp.dto.AverageTemperature;
import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.exception.EmptyWeatherRepositoryException;
import com.example.weatherapp.exception.NoWeatherInformationException;
import com.example.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {
    @Mock
    private WeatherDto weatherDto;
    @Mock
    private DateRange dateRange;
    @Mock
    private AverageTemperature averageTemperature;
    @Mock
    private WeatherService weatherService;
    @InjectMocks
    private WeatherController weatherController;

    @Test
    void getLatestForecastThenShouldReturnDto() {
        Mockito.when(weatherService.getLatestForecast()).thenReturn(weatherDto);
        Assertions.assertEquals(new ResponseEntity<>(weatherDto, HttpStatus.OK), weatherController.getLatestForecast());
        Mockito.verify(weatherService).getLatestForecast();
    }

    @Test
    void getLatestForecastThenShouldThrowException() {
        Mockito.when(weatherService.getLatestForecast()).thenThrow(EmptyWeatherRepositoryException.class);
        Assertions.assertThrows(EmptyWeatherRepositoryException.class, () -> weatherController.getLatestForecast());
        Mockito.verify(weatherService).getLatestForecast();
    }

    @Test
    void getAverageTemperatureThenShouldReturnTemp() {
        Mockito.when(weatherService.calcAverage(dateRange)).thenReturn(averageTemperature);
        Assertions.assertEquals(new ResponseEntity<>(averageTemperature, HttpStatus.OK),
                weatherController.getAverageTemperature(dateRange));
        Mockito.verify(weatherService).calcAverage(Mockito.any());
    }

    @Test
    void getAverageTemperatureThenShouldThrowException() {
        Mockito.when(weatherService.calcAverage(dateRange)).thenThrow(NoWeatherInformationException.class);
        Assertions.assertThrows(NoWeatherInformationException.class,
                () -> weatherController.getAverageTemperature(dateRange));
        Mockito.verify(weatherService).calcAverage(Mockito.any());
    }
}