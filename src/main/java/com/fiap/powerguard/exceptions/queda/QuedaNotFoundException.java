package com.fiap.powerguard.exceptions.queda;

public class QuedaNotFoundException extends RuntimeException{
    public QuedaNotFoundException(String mensagem){
        super(mensagem);
    }
}
