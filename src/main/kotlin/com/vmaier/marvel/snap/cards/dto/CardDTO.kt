package com.vmaier.marvel.snap.cards.dto

data class CardDTO(
    val id: Int,
    val name: String,
    val cost: Int,
    val power: Int,
    val ability: String?,
    val url: String?
)