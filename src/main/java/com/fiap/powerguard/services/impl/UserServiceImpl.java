package com.fiap.powerguard.services.impl;

import com.fiap.powerguard.dto.UserDTO;
import com.fiap.powerguard.integration.ViaCepService;
import com.fiap.powerguard.model.User;
import com.fiap.powerguard.repository.UserRepository;
import com.fiap.powerguard.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ViaCepService viaCepService;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getCep() != null) {
            String city = viaCepService.getCityByCep(userDTO.getCep())
                    .block();
            userDTO.setCity(city);

            checkEnergyAlerts(city, userDTO.getEmail());
        }

        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

}
