package com.example.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class WeatherDto implements Serializable {
    private final Integer temperature;
    @JsonProperty("wind_speed")
    private final Integer windSpeed;
    private final Integer pressure;
    private final Integer humidity;
    private final String condition;
    private final String location;
}