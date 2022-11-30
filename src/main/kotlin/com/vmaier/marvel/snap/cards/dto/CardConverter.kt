package com.vmaier.marvel.snap.cards.dto

import com.vmaier.marvel.snap.cards.db.model.Card
import java.util.stream.Collector
import java.util.stream.Collectors

object CardConverter {

    fun convertToDto(card: Card): CardDto {
        return CardDto(card.name, card.energy, card.power, card.ability)
    }

    fun convertToDto(cards: List<Card>): List<CardDto> {
        return cards.stream()
            .map { card -> convertToDto(card) }
            .collect(Collectors.toList())
    }

    fun convertToModel(cardDto: CardDto): Card {
        return Card(cardDto.name, cardDto.energy, cardDto.power, cardDto.ability)
    }
}