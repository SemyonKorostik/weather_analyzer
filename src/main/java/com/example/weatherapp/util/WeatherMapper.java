package com.example.weatherapp.util;

import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.entity.Weather;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WeatherMapper {
    public WeatherDto toDto(Weather entity) {
        return WeatherDto.builder()
                .temperature(entity.getTemperature())
                .condition(entity.getCondition())
                .humidity(entity.getHumidity())
                .location(entity.getLocation())
                .pressure(entity.getPressure())
                .windSpeed(entity.getWindSpeed())
                .build();
    }
}
