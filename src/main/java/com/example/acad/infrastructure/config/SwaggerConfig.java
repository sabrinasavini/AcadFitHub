package com.example.acad.infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI academiaOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Academia FitHub")
                        .description("Cadastro e gestão de clientes, planos e fichas de treino")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Equipe FitHub")
                                .email("suporteacad@gmail.com"))
                );
    }

}
