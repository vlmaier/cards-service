package com.vmaier.marvel.snap.cards.service

import com.vmaier.marvel.snap.cards.db.repo.CardRepository
import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.dto.CardResponse
import com.vmaier.marvel.snap.cards.dto.CreateCardRequest
import org.springframework.stereotype.Service

@Service
class CardsService constructor(private val cardRepository: CardRepository) {

    fun getAllCards(): List<CardResponse> {
        val cards = cardRepository.findAll().toList()
        return CardConverter.convertToDto(cards).sortedBy { card -> card.name }
    }

    fun addNewCard(request: CreateCardRequest): CardResponse {
        val card = CardConverter.convertToModel(request)
        val newCard = cardRepository.save(card)
        return CardConverter.convertToDto(newCard)
    }
}