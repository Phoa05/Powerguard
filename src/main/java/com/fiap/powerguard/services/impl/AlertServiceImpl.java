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
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AlertDTO createAlert(AlertDTO alertDTO) {
        User user = userRepository.findById(alertDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + alertDTO.getUserId()));

        Alert alert = modelMapper.map(alertDTO, Alert.class);
        alert.setUser(user);
        alert.setTimestamp(LocalDateTime.now());
        alert.setAcknowledged(false);

        Alert savedAlert = alertRepository.save(alert);
        return modelMapper.map(savedAlert, AlertDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlertDTO> getAlertsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return alertRepository.findByUser(user).stream()
                .map(alert -> modelMapper.map(alert, AlertDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AlertDTO acknowledgeAlert(Long alertId) {
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() -> new EntityNotFoundException("Alert not found with id: " + alertId));

        alert.setAcknowledged(true);
        Alert updatedAlert = alertRepository.save(alert);
        return modelMapper.map(updatedAlert, AlertDTO.class);
    }

}
