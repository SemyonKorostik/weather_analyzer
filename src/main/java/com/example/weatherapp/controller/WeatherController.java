package com.example.weatherapp.controller;

import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/current")
    public ResponseEntity<WeatherDto> getLatestForecast() {
        return new ResponseEntity<>(weatherService.getLatestForecast(), HttpStatus.OK);
    }

}
