package com.vmaier.marvel.snap.cards.openapi.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for displaying list of a cards")
data class CardListResponse(

    @field:Schema(description = "Amount of available items")
    val total: Int,

    @field:Schema(description = "List of requested cards")
    val cards: List<CardResponse>
)