package com.fiap.powerguard.integration;

import com.fiap.powerguard.dto.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCepService {
    private final WebClient webClient;

    public ViaCepService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    public ViaCepResponse buscarPorCep(String cep) {
        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(ViaCepResponse.class)
                .block();  // Simplificação para MVP (evite block() em produção)
    }
}
