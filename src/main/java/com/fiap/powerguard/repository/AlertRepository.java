package com.fiap.powerguard.repository;

import com.fiap.powerguard.model.Alert;
import com.fiap.powerguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUser(User user);
}
