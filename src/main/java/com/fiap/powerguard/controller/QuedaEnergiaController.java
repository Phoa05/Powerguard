package com.fiap.powerguard.controller;

import com.fiap.powerguard.dtos.QuedaEnergiaDTO;
import com.fiap.powerguard.models.QuedaEnergia;
import com.fiap.powerguard.services.QuedaEnergiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/queda-energia")
public class QuedaEnergiaController {
    private final QuedaEnergiaService quedaEnergiaService;

    public QuedaEnergiaController(QuedaEnergiaService quedaEnergiaService) {
        this.quedaEnergiaService = quedaEnergiaService;
    }

    @PostMapping
    public ResponseEntity<QuedaEnergia> reportarQueda(@RequestBody QuedaEnergiaDTO quedaDTO) {
        QuedaEnergia queda = quedaEnergiaService.reportarQueda(quedaDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(queda.getId())
                .toUri();

        return ResponseEntity.created(location).body(queda);
    }

    @GetMapping("/por-cep/{cep}")
    public ResponseEntity<List<QuedaEnergia>> buscarPorCep(@PathVariable String cep) {
        List<QuedaEnergia> quedas = quedaEnergiaService.buscarPorCep(cep);
        return ResponseEntity.ok(quedas);
    }
}
