package com.example.weatherapp.service;

import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.exception.WeatherAPIRequestException;
import com.example.weatherapp.repository.WeatherRepository;
import com.example.weatherapp.util.WeatherParser;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    @Value("${weather.api.url.template}")
    private String urlTemplate;
    @Value("${weather.api.key}")
    private String apiKeyValue;
    @Value("${weather.api.host}")
    private String apiHostValue;
    private final static String API_KEY = "X-RapidAPI-Key";
    private final static String API_HOST = "X-RapidAPI-Host";

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    private void saveCurrentWeather() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlTemplate)
                .get()
                .addHeader(API_KEY, apiKeyValue)
                .addHeader(API_HOST, apiHostValue)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Weather weather = WeatherParser.parse(Objects.requireNonNull(response.body()).string());
            if (!weatherRepository.existsByCreateDate(weather.getCreateDate())) {
                weatherRepository.save(weather);
            }
        } catch (IOException e) {
            throw new WeatherAPIRequestException();
        }
    }
}
