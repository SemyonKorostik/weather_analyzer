package com.example.weatherapp.controller;

import com.example.weatherapp.dto.AverageTemperature;
import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.service.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/current")
    public ResponseEntity<WeatherDto> getLatestForecast() {
        return new ResponseEntity<>(weatherService.getLatestForecast(), HttpStatus.OK);
    }

    @PostMapping("/average/temp")
    public ResponseEntity<AverageTemperature> getAverageTemperature(@Valid @RequestBody DateRange range) {
        return new ResponseEntity<>(weatherService.calcAverage(range), HttpStatus.OK);
    }

}
