package com.fiap.powerguard.controller;

import com.fiap.powerguard.dto.AlertDTO;
import com.fiap.powerguard.services.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService alertService;

    @PostMapping("/check-weather")
    public ResponseEntity<String> checkWeatherAlerts() {
        alertService.checkWeatherAlerts();
        return ResponseEntity.ok("Verificação de tempestades concluída!");
    }
}
