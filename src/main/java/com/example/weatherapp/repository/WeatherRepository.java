package com.example.weatherapp.repository;

import com.example.weatherapp.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    boolean existsByCreateDate(LocalDateTime createDate);
}
