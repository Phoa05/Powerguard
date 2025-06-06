package com.fiap.powerguard.exceptions.cep;

public class CepNaoEncontradoException extends RuntimeException{
    public CepNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
