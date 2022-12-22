package com.vmaier.marvel.snap.cards.db.dao

import jakarta.persistence.*

@Table(name = "cards")
@Entity
data class Card(
    val name: String,
    val cost: Int,
    val power: Int,
    val ability: String? = null,
    val url: String? = null,
    val isOwned: Boolean = false,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)