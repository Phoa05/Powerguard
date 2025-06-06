package com.fiap.powerguard.services;

import com.fiap.powerguard.dtos.QuedaEnergiaDTO;
import com.fiap.powerguard.models.QuedaEnergia;

import java.util.List;

public interface QuedaEnergiaService {
    QuedaEnergia reportarQueda(QuedaEnergiaDTO quedaDTO);
    List<QuedaEnergia> buscarPorCep(String cep);
    List<QuedaEnergia> buscarPorUsuario(Long userId);
    List<QuedaEnergia> buscarPorStatus(String status);
    QuedaEnergia atualizarStatus(Long id, String status);
}
