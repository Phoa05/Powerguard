package com.fiap.powerguard.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class QuedaEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String cepAfetado;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCepAfetado() {
        return cepAfetado;
    }

    public void setCepAfetado(String cepAfetado) {
        this.cepAfetado = cepAfetado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
