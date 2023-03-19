package com.example.weatherapp.mapper;

import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.entity.Weather;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherDto toDto(Weather entity);
}
