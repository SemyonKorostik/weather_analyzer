package com.example.weatherapp.service.impl;

import com.example.weatherapp.dto.AverageTemperature;
import com.example.weatherapp.dto.DateRange;
import com.example.weatherapp.dto.WeatherDto;
import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.exception.NoWeatherInformation;
import com.example.weatherapp.exception.WeatherAPIRequestException;
import com.example.weatherapp.repository.WeatherRepository;
import com.example.weatherapp.service.WeatherService;
import com.example.weatherapp.service.WeatherValidationService;
import com.example.weatherapp.mapper.WeatherMapper;
import com.example.weatherapp.util.WeatherParser;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherValidationService weatherValidationService;
    private final WeatherMapper weatherMapper;
    @Value("${weather.api.location}")
    private String location;
    @Value("${weather.api.key}")
    private String apiKeyValue;
    @Value("${weather.api.host}")
    private String apiHostValue;
    private final static String urlTemplate = "https://weatherapi-com.p.rapidapi.com/current.json?q=";
    private final static String API_KEY = "X-RapidAPI-Key";
    private final static String API_HOST = "X-RapidAPI-Host";

    @Override
    public WeatherDto getLatestForecast() {
        return weatherMapper.toDto(weatherRepository.findWithLatestDate());
    }

    @Override
    public AverageTemperature calcAverage(DateRange range) {
        weatherValidationService.validateDateRange(range);
        LocalDateTime from = LocalDateTime.of(range.getFrom(), LocalTime.MIN);
        LocalDateTime to = LocalDateTime.of(range.getTo(), LocalTime.MAX);
        if(!weatherRepository.existsByCreateDate(from)) {
            throw new NoWeatherInformation(range.getFrom().toString());
        }
        return new AverageTemperature((int) Math.round(weatherRepository.findByCreateDateBetween(from, to)
                .stream()
                .mapToInt(Weather::getTemperature)
                .average()
                .orElseThrow()));
    }

    @Override
    public void saveCurrentWeather() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlTemplate + location)
                .get()
                .addHeader(API_KEY, apiKeyValue)
                .addHeader(API_HOST, apiHostValue)
                .build();
        Weather weather;
        try (Response response = client.newCall(request).execute()) {
            weather = WeatherParser.parse(Objects.requireNonNull(response.body()).string());
        } catch (Exception e) {
            throw new WeatherAPIRequestException(e.getMessage());
        }
        if (!weatherRepository.existsByCreateDate(weather.getCreateDate())) {
            weatherRepository.save(weather);
        }
    }
}
