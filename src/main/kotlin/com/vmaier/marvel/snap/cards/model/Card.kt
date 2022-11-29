package com.vmaier.marvel.snap.cards.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Card(
    val name: String,
    val energy: Int,
    val power: Int,
    val ability: String,
    @Id @GeneratedValue val id: Int? = null)