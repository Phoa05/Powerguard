package com.fiap.powerguard.services.impl;

import com.fiap.powerguard.exceptions.queda.QuedaNotFoundException;
import com.fiap.powerguard.exceptions.usuario.UsuarioNotFoundException;
import com.fiap.powerguard.dtos.QuedaEnergiaDTO;
import com.fiap.powerguard.models.QuedaEnergia;
import com.fiap.powerguard.models.User;
import com.fiap.powerguard.repository.QuedaEnergiaRepository;
import com.fiap.powerguard.repository.UserRepository;
import com.fiap.powerguard.services.QuedaEnergiaService;
import com.fiap.powerguard.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuedaEnergiaServiceImpl implements QuedaEnergiaService {
    @Autowired
    private QuedaEnergiaRepository quedaEnergiaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public QuedaEnergia reportarQueda(QuedaEnergiaDTO quedaDTO) {
        User user = userRepository.findById(quedaDTO.getUserId())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

        QuedaEnergia queda = new QuedaEnergia();
        queda.setDataHoraInicio(quedaDTO.getDataHoraInicio() != null ?
                quedaDTO.getDataHoraInicio() : LocalDateTime.now());
        queda.setDataHoraFim(quedaDTO.getDataHoraFim());
        queda.setDescricao(quedaDTO.getDescricao());
        queda.setUser(user);
        queda.setCepAfetado(quedaDTO.getCepAfetado());
        queda.setStatus("reportada");

        return quedaEnergiaRepository.save(queda);
    }

    @Override
    public List<QuedaEnergia> buscarPorCep(String cep) {
        List<QuedaEnergia> listaPorCep = quedaEnergiaRepository.findByCepAfetado(cep);

        if(listaPorCep.isEmpty()){
            throw new QuedaNotFoundException("Não foram encontradas quedas para este cep");
        }
        return listaPorCep;
    }

    @Override
    public List<QuedaEnergia> buscarPorUsuario(Long userId) {
        return quedaEnergiaRepository.findByUserId(userId);
    }

    @Override
    public List<QuedaEnergia> buscarPorStatus(String status) {
        return quedaEnergiaRepository.findByStatus(status);
    }

    @Override
    public QuedaEnergia atualizarStatus(Long id, String status) {
        QuedaEnergia queda = quedaEnergiaRepository.findById(id)
                .orElseThrow(() -> new QuedaNotFoundException("Queda de energia não encontrada"));

        queda.setStatus(status);

        if ("resolvida".equalsIgnoreCase(status) && queda.getDataHoraFim() == null) {
            queda.setDataHoraFim(LocalDateTime.now());
        }

        return quedaEnergiaRepository.save(queda);
    }
}
