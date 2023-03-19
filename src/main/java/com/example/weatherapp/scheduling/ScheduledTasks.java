package com.example.weatherapp.scheduling;

import com.example.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@EnableScheduling
@Component
public class ScheduledTasks {
    private final WeatherService weatherService;

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void executeWeatherSaving() {
        weatherService.saveCurrentWeather();
    }
}
