package com.vmaier.marvel.snap.cards.db.model

import jakarta.persistence.*

@Table(name = "cards")
@Entity
data class Card(
    val name: String,
    val cost: Int,
    val power: Int,
    val ability: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val url: String? = null)