package com.fiap.powerguard.services;

import com.fiap.powerguard.dtos.UserDTO;
import com.fiap.powerguard.models.User;

public interface UserService {
    User cadastrarUsuario(UserDTO userDTO);
    User buscarUsuarioPorId(Long id);
    User buscarUsuarioPorCpf(String cpf);
}
