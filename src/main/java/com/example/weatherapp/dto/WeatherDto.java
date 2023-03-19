package com.example.weatherapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class WeatherDto implements Serializable {
    @JsonProperty("temp_c")
    @NotNull
    private Integer temperature;
    @JsonProperty("wind_speed_mph")
    @NotNull
    @Digits(integer = 3, fraction = 1)
    private Double windSpeed;
    @JsonProperty("pressure_mb")
    @NotNull
    private Integer pressure;
    @JsonProperty("humidity")
    @NotNull
    @Size(max = 100)
    private Integer humidity;
    @JsonProperty("condition")
    @NotNull
    private String condition;
    @JsonProperty("location")
    @NotNull
    private String location;
}