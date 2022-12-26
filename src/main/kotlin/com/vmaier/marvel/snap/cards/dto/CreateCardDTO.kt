package com.vmaier.marvel.snap.cards.dto

import org.springframework.web.multipart.MultipartFile

data class CreateCardDTO(
    val name: String? = null,
    val cost: Int? = null,
    val power: Int? = null,
    val ability: String? = null,
    val series: String? = null,
    val image: MultipartFile? = null
)