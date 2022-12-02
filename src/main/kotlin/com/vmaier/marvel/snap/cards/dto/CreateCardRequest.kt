package com.vmaier.marvel.snap.cards.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for creating a new card")
data class CreateCardRequest(

    @field:Schema(
        description = "Name of the character"
    )
    val name: String? = null,

    @field:Schema(
        description = "Amount of energy needs to be used for playing this card"
    )
    val energy: Int? = null,

    @field:Schema(
        description = "Default amount of power of the card"
    )
    val power: Int? = null,

    @field:Schema(
        description = "Description of the ability the character of the card has"
    )
    val ability: String? = null

)