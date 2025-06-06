package com.fiap.powerguard.services;

import com.fiap.powerguard.dtos.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {
    private final WebClient webClient;

    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br/ws").build();
    }

    public Mono<ViaCepResponse> buscarEnderecoPorCep(String cep) {
        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(ViaCepResponse.class);
    }
}
