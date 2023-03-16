package com.example.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class WeatherDto implements Serializable {
    private final Integer temperature;
    private final Integer windSpeed;
    private final Integer pressure;
    private final Integer humidity;
    private final String condition;
    private final String location;

    @JsonCreator
    public WeatherDto(Integer temperature, Integer windSpeed, Integer pressure, Integer humidity, String condition, 
                      String location) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.humidity = humidity;
        this.condition = condition;
        this.location = location;
    }
}