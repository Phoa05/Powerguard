package com.fiap.powerguard.services;

import com.fiap.powerguard.dtos.ViaCepResponse;
import com.fiap.powerguard.exceptions.cep.CepInvalidoException;
import com.fiap.powerguard.exceptions.cep.CepNaoEncontradoException;
import com.fiap.powerguard.exceptions.cep.ServicoIndisponivelException;
import org.springframework.http.HttpStatusCode;
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
        if (cep == null || !cep.matches("\\d{8}")) {
            return Mono.error(new CepInvalidoException("Formato de CEP inválido. Deve conter 8 dígitos"));
        }

        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new ServicoIndisponivelException("Não foi possível consultar cep, tente novamente mais tarde!")))
                .bodyToMono(ViaCepResponse.class);

    }
}
