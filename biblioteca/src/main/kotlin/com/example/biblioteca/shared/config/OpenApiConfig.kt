package com.example.biblioteca.shared.config


import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Biblioteca")
                    .description("API para gestão de acervo de livros usando Arquitetura Hexagonal")
                    .version("1.0.0")
            )
    }
}