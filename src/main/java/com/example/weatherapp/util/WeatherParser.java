package com.example.weatherapp.util;

import com.example.weatherapp.entity.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class WeatherParser {
    private final String datePattern = "yyyy-MM-dd HH:mm";

    public Weather parse(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(datePattern);
        JsonNode allNodes = mapper.readTree(json);
        JsonNode locationNode = allNodes.get("location");
        JsonNode currentNode = allNodes.get("current");
        JsonNode conditionNode = currentNode.get("condition");
        return Weather.builder()
                .temperature(currentNode.get("temp_c").asInt())
                .windSpeed(toMetersPerHour(currentNode.get("wind_kph").asDouble()))
                .pressure(currentNode.get("pressure_mb").asInt())
                .humidity(currentNode.get("humidity").asInt())
                .createDate(LocalDateTime.parse(currentNode.get("last_updated").asText(), pattern))
                .condition(conditionNode.get("text").asText())
                .location(locationNode.get("name").asText())
                .build();
    }

    private Integer toMetersPerHour(double kilometersPerHour) {
        return (int) (kilometersPerHour * 1000);
    }
}
