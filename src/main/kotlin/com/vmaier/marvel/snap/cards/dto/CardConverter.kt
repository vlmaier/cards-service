package com.vmaier.marvel.snap.cards.dto

import com.vmaier.marvel.snap.cards.db.dao.Card
import com.vmaier.marvel.snap.cards.openapi.model.CardResponse
import com.vmaier.marvel.snap.cards.openapi.model.CreateCardRequest
import java.util.stream.Collectors

object CardConverter {

    fun convertToModel(card: Card): CardResponse {
        return CardResponse(card.id!!, card.name, card.cost, card.power, card.ability, card.url)
    }

    fun convertToModel(cards: List<Card>): List<CardResponse> {
        return cards.stream()
            .map { card -> convertToModel(card) }
            .collect(Collectors.toList())
    }

    fun convertToDao(request: CreateCardDTO): Card {
        return Card(request.name!!, request.cost!!, request.power!!, request.ability!!)
    }

    fun convertToDao(request: CreateCardRequest): Card {
        return Card(request.name!!, request.cost!!, request.power!!, request.ability, request.imageUrl!!)
    }
}