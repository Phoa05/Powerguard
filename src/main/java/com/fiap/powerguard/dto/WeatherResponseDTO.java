package com.fiap.powerguard.dto;

import lombok.Data;

@Data
public class WeatherResponseDTO {
    private Weather[] weather;

    @Data
    public static class Weather {
        private int id;
        private String main;
        private String description;
    }
}
