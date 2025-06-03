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

    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        return ResponseEntity.ok(alertService.createAlert(alertDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AlertDTO>> getUserAlerts(@PathVariable Long userId) {
        return ResponseEntity.ok(alertService.getAlertsByUserId(userId));
    }
}
