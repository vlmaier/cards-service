package com.vmaier.marvel.snap.cards.openapi.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for creating a new card")
data class CreateCardRequest(

    @field:Schema(description = "Name of the character")
    val name: String,

    @field:Schema(description = "Amount of energy cost for playing this card")
    val cost: Int,

    @field:Schema(description = "Amount of power when played")
    val power: Int,

    @field:Schema(description = "Description of the ability the character of the card has. Can be empty.")
    val ability: String?,

    @field:Schema(description = "Card image URL")
    val imageUrl: String
)