package com.fiap.powerguard.services;

import com.fiap.powerguard.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
}
