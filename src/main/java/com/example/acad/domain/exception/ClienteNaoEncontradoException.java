package com.example.acad.domain.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
