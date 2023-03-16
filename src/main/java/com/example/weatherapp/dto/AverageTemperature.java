package com.example.weatherapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AverageTemperature implements Serializable {
    private Integer average_temp;

}
