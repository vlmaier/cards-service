package com.vmaier.marvel.snap.cards.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

@Schema(description = "Model for creating a new card")
data class CreateCardRequest(

    @field:Schema(
        description = "Name of the character"
    )
    val name: String? = null,

    @field:Schema(
        description = "Amount of energy cost for playing this card"
    )
    val cost: Int? = null,

    @field:Schema(
        description = "Default amount of power of the card"
    )
    val power: Int? = null,

    @field:Schema(
        description = "Description of the ability the character of the card has"
    )
    val ability: String? = null,

    @field:Schema(
        description = "Image file for the card"
    )
    val image: MultipartFile? = null
)