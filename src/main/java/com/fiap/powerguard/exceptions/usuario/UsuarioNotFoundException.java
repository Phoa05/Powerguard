package com.fiap.powerguard.exceptions.usuario;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String mensagem){
        super(mensagem);
    }
}
