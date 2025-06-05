package com.fiap.powerguard.services.impl;

import com.fiap.powerguard.dto.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherAlertService {
    private final WebClient webClient;

    public WeatherAlertService(@Value("${openweather.api.url}") String apiUrl,
                               @Value("${openweather.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public boolean hasStormAlert(String city) {
        WeatherResponseDTO response = webClient.get()
                .uri("/weather?q={city}&appid={apiKey}&units=metric&lang=pt",
                        city, "${openweather.api.key}")
                .retrieve()
                .bodyToMono(WeatherResponseDTO.class)
                .block();

        return response.getWeather()[0].getMain().equalsIgnoreCase("Thunderstorm");
    }
}
