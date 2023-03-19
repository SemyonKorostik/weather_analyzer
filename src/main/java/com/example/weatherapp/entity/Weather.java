package com.example.weatherapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "temp_c", nullable = false)
    private Integer temperature;

    @Column(name = "wind_speed_mph", nullable = false)
    private Double windSpeed;

    @Column(name = "pressure_mb", nullable = false)
    private Integer pressure;

    @Column(name = "humidity", nullable = false)
    private Integer humidity;

    @Column(name = "condition", nullable = false, length = 50)
    private String condition;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return id.equals(weather.id) && temperature.equals(weather.temperature) &&
                windSpeed.equals(weather.windSpeed) && pressure.equals(weather.pressure) &&
                humidity.equals(weather.humidity) && condition.equals(weather.condition) &&
                location.equals(weather.location) && createDate.equals(weather.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, windSpeed, pressure, humidity, condition, location, createDate);
    }
}