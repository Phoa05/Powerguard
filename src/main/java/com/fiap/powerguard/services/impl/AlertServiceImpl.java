package com.fiap.powerguard.services.impl;

import com.fiap.powerguard.dto.AlertDTO;
import com.fiap.powerguard.model.Alert;
import com.fiap.powerguard.model.User;
import com.fiap.powerguard.repository.AlertRepository;
import com.fiap.powerguard.repository.UserRepository;
import com.fiap.powerguard.services.AlertService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final UserRepository userRepository;
    private final WeatherAlertService weatherAlertService;

    @Override
    @Transactional
    public void checkWeatherAlerts() {
        userRepository.findAll().forEach(user -> {
            if (weatherAlertService.hasStormAlert(user.getCity())) {
                Alert alert = new Alert();
                alert.setUser(user);
                alert.setMessage("ALERTA: Tempestade forte prevista em " + user.getCity());
                alert.setTimestamp(LocalDateTime.now());
                alertRepository.save(alert);
            }
        });
    }
}
