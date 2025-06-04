package com.fiap.powerguard.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ViaCepService {
    private final WebClient webClient;

    public ViaCepService(@Value("${viacep.api.url}") String viaCepUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(viaCepUrl)
                .build();
    }

    public Mono<String> getCityByCep(String cep) {
        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(ViaCepResponse.class)
                .flatMap(response -> {
                    if (response.getLocalidade() == null) {
                        return Mono.error(new CepNotFoundException("CEP não encontrado"));
                    }
                    return Mono.just(response.getLocalidade());
                });
    }
}
