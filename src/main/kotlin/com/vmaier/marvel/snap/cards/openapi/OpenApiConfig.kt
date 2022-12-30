package com.vmaier.marvel.snap.cards.openapi

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .addServersItem(Server().url("/"))
            .info(
                Info()
                    .title("Marvel SNAP Cards API")
                    .description("Overview of all available and documented REST APIs for handling Marvel SNAP cards database.")
            )
    }
}