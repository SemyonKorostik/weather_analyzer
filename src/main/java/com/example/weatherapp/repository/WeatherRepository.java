package com.example.weatherapp.repository;

import com.example.weatherapp.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    boolean existsByCreateDate(LocalDateTime createDate);

    @Query(value = "select * from weather where create_date = (select max(create_date) from weather) ", nativeQuery = true)
    Weather findWithLatestDate();
}
