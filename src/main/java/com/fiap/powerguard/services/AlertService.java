package com.fiap.powerguard.services;

import com.fiap.powerguard.dto.AlertDTO;

import java.util.List;

public interface AlertService {
    AlertDTO createAlert(AlertDTO alertDTO);
    List<AlertDTO> getAlertsByUserId(Long userId);
    AlertDTO acknowledgeAlert(Long alertId);
}
