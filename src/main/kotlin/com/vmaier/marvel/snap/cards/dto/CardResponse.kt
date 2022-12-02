package com.vmaier.marvel.snap.cards.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for viewing information of a card")
data class CardResponse(

    @field:Schema(
        description = "Name of the character"
    )
    val name: String,

    @field:Schema(
        description = "Amount of energy needs to be used for playing this card"
    )
    val energy: Int,

    @field:Schema(
        description = "Default amount of power of the card"
    )
    val power: Int,

    @field:Schema(
        description = "Description of the ability the character of the card has"
    )
    val ability: String

)