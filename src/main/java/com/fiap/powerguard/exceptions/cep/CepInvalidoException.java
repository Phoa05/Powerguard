package com.fiap.powerguard.exceptions.cep;

public class CepInvalidoException extends RuntimeException{
    public CepInvalidoException(String mensagem){
        super(mensagem);
    }
}
