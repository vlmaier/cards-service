package com.vmaier.marvel.snap.cards.openapi.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class ErrorResponse(

    @field:Schema(description = "HTTP status code")
    val statusCode: Int,

    @field:Schema(description = "Error message for the client")
    val message: String?
)