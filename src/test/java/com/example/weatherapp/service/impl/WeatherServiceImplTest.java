package com.example.weatherapp.service.impl;

import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.exception.EmptyWeatherRepositoryException;
import com.example.weatherapp.exception.NoWeatherInformationException;
import com.example.weatherapp.mapper.WeatherMapper;
import com.example.weatherapp.repository.WeatherRepository;
import com.example.weatherapp.service.WeatherValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {
    @Mock
    private Weather weather;
    @Mock
    private WeatherDto weatherDto;
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WeatherValidationService weatherValidationService;
    @Mock
    private WeatherMapper weatherMapper;
    @InjectMocks
    private WeatherServiceImpl weatherService;
    private static final Integer WEATHER_ID = 1;
    private static final String CONDITION = "Sunny";
    private static final LocalDateTime CREATE_DATE = LocalDateTime.now();
    private static final Integer HUMIDITY = 56;
    private static final String LOCATION = "Minsk";
    private static final Integer PRESSURE = 1000;
    private static final Double WIND_SPEED = 10.1;

    @Test
    void whenGetLatestForecastThenShouldReturnDto() {
        Mockito.when(weatherRepository.findAll()).thenReturn(List.of(weather));
        Mockito.when(weatherRepository.findWithLatestDate()).thenReturn(weather);
        Mockito.when(weatherMapper.toDto(weather)).thenReturn(weatherDto);
        Assertions.assertEquals(weatherDto, weatherService.getLatestForecast());
        Mockito.verify(weatherRepository).findAll();
        Mockito.verify(weatherRepository).findWithLatestDate();
        Mockito.verify(weatherMapper).toDto(Mockito.any(Weather.class));
    }

    @Test
    void whenGetLatestForecastThenShouldThrowException() {
        Mockito.when(weatherRepository.findAll()).thenReturn(List.of());
        Assertions.assertThrows(EmptyWeatherRepositoryException.class, () -> weatherService.getLatestForecast());
        Mockito.verify(weatherRepository).findAll();
    }

    @Test
    void calcAverageThenShouldReturnInteger() {
        int temp1 = 7;
        int temp2 = 13;
        int average = (temp1 + temp2) / 2;
        Weather weather1 = Weather.builder()
                .id(WEATHER_ID)
                .condition(CONDITION)
                .createDate(CREATE_DATE)
                .humidity(HUMIDITY)
                .location(LOCATION)
                .pressure(PRESSURE)
                .temperature(temp1)
                .windSpeed(WIND_SPEED)
                .build();
        Weather weather2 = Weather.builder()
                .id(WEATHER_ID)
                .condition(CONDITION)
                .createDate(CREATE_DATE)
                .humidity(HUMIDITY)
                .location(LOCATION)
                .pressure(PRESSURE)
                .temperature(temp2)
                .windSpeed(WIND_SPEED)
                .build();
        DateRange range = new DateRange(LocalDate.now(), LocalDate.now());
        Mockito.doNothing().when(weatherValidationService).validateDateRange(Mockito.any());
        Mockito.when(weatherRepository.existsByCreateDate(Mockito.any())).thenReturn(Boolean.TRUE);
        Mockito.when(weatherRepository.findByCreateDateBetween(Mockito.any(), Mockito.any())).thenReturn(List.of(weather1, weather2));
        Assertions.assertEquals(average, weatherService.calcAverage(range).getAverage_temp());
        Mockito.verify(weatherValidationService).validateDateRange(Mockito.any());
        Mockito.verify(weatherRepository).existsByCreateDate(Mockito.any());
        Mockito.verify(weatherRepository).findByCreateDateBetween(Mockito.any(), Mockito.any());
    }

    @Test
    void calcAverageThenShouldThrowException() {
        DateRange range = new DateRange(LocalDate.now(), LocalDate.now());
        Mockito.doNothing().when(weatherValidationService).validateDateRange(Mockito.any());
        Mockito.when(weatherRepository.existsByCreateDate(Mockito.any())).thenReturn(Boolean.FALSE);
        Assertions.assertThrows(NoWeatherInformationException.class, () -> weatherService.calcAverage(range));
        Mockito.verify(weatherValidationService).validateDateRange(Mockito.any());
        Mockito.verify(weatherRepository).existsByCreateDate(Mockito.any());
    }

}