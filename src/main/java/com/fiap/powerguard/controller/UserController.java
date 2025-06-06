package com.fiap.powerguard.controller;

import com.fiap.powerguard.dtos.UserDTO;
import com.fiap.powerguard.models.User;
import com.fiap.powerguard.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> cadastrarUsuario(@RequestBody UserDTO userDTO) {
        User user = userService.cadastrarUsuario(userDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarUsuario(@PathVariable Long id) {
        User user = userService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/por-cpf/{cpf}")
    public ResponseEntity<User> buscarPorCpf(@PathVariable String cpf) {
        User user = userService.buscarUsuarioPorCpf(cpf);
        return ResponseEntity.ok(user);
    }
}
