package com.fiap.powerguard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertDTO {
    private Long id;
    private Long userId;
    private LocalDateTime timestamp;
    private String message;
    private boolean acknowledged;
}
