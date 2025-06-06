package com.fiap.powerguard.services.impl;

import com.fiap.powerguard.dtos.UserDTO;
import com.fiap.powerguard.dtos.ViaCepResponse;
import com.fiap.powerguard.models.User;
import com.fiap.powerguard.repository.UserRepository;
import com.fiap.powerguard.services.UserService;
import com.fiap.powerguard.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public User cadastrarUsuario(UserDTO userDTO) {
        if (userRepository.existsByCpf(userDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        ViaCepResponse endereco = viaCepService.buscarEnderecoPorCep(userDTO.getCep()).block();

        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());
        user.setTelefone(userDTO.getTelefone());
        user.setCpf(userDTO.getCpf());
        user.setCep(userDTO.getCep());

        if (endereco != null) {
            user.setLogradouro(endereco.getLogradouro());
            user.setComplemento(endereco.getComplemento());
            user.setBairro(endereco.getBairro());
            user.setLocalidade(endereco.getLocalidade());
            user.setUf(endereco.getUf());
        }

        return userRepository.save(user);
    }

    @Override
    public User buscarUsuarioPorId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Override
    public User buscarUsuarioPorCpf(String cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
