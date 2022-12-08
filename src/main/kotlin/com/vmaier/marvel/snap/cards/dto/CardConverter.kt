package com.vmaier.marvel.snap.cards.dto

import com.vmaier.marvel.snap.cards.db.model.Card
import java.util.stream.Collectors

object CardConverter {

    fun convertToDto(card: Card): CardResponse {
        return CardResponse(card.id!!, card.name, card.energy, card.power, card.ability, card.url)
    }

    fun convertToDto(cards: List<Card>): List<CardResponse> {
        return cards.stream()
            .map { card -> convertToDto(card) }
            .collect(Collectors.toList())
    }

    fun convertToModel(request: CreateCardRequest): Card {
        return Card(request.name!!, request.energy!!, request.power!!, request.ability!!)
    }
}