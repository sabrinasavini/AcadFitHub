package com.example.acad.application.dto;

public class AuthDTO {

    public record LoginRequest(
        String email,
        String senha
    ) {}

    public record TokesResponse(
        String token
    ) {}

}
