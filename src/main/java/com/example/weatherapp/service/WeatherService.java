package com.example.weatherapp.service;

import com.example.weatherapp.dto.AverageTemperature;
import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.dto.WeatherDto;

public interface WeatherService {
    WeatherDto getLatestForecast();

    AverageTemperature calcAverage(DateRange range);

    void saveCurrentWeather();
}
