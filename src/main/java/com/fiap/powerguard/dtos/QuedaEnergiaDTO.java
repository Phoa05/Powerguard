package com.fiap.powerguard.dtos;


import java.time.LocalDateTime;

public class QuedaEnergiaDTO {
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String descricao;
    private Long userId;
    private String cepAfetado;

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCepAfetado() {
        return cepAfetado;
    }

    public void setCepAfetado(String cepAfetado) {
        this.cepAfetado = cepAfetado;
    }
}
