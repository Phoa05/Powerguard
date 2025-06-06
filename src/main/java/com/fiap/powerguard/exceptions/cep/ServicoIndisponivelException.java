package com.fiap.powerguard.exceptions.cep;

public class ServicoIndisponivelException extends RuntimeException{
    public ServicoIndisponivelException(String mensagem){
        super(mensagem);
    }
}
