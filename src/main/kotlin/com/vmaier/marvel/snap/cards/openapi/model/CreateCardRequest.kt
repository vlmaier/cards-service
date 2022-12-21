package com.vmaier.marvel.snap.cards.openapi.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for creating a new card")
data class CreateCardRequest(

    @field:Schema(description = "Name of the character", required = true, maxLength = 255)
    val name: String?,

    @field:Schema(
        description = "Amount of energy cost for playing this card",
        required = true,
        minimum = "0",
        maximum = "10"
    )
    val cost: Int?,

    @field:Schema(description = "Amount of power when played", required = true, minimum = "-20", maximum = "20")
    val power: Int?,

    @field:Schema(
        description = "Description of the ability the character of the card has. Can be empty.",
        nullable = true,
        maxLength = 1024
    )
    val ability: String?,

    @field:Schema(description = "Card image URL", required = true, maxLength = 255)
    val imageUrl: String?
)