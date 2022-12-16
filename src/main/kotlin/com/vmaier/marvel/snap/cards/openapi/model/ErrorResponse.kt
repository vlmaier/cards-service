package com.vmaier.marvel.snap.cards.openapi.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class ErrorResponse(

    @field:Schema
    val statusCode: Int,

    @field:Schema
    val message: String?
)