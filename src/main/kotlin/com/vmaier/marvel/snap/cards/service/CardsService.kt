package com.vmaier.marvel.snap.cards.service

import com.vmaier.marvel.snap.cards.db.repo.CardRepository
import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.dto.CardResponse
import com.vmaier.marvel.snap.cards.dto.CreateCardRequest
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardsService constructor(
    private val cardRepository: CardRepository,
    private val s3Service: S3Service) {

    fun getAllCards(): List<CardResponse> {
        val cards = cardRepository.findAll().toList()
        return CardConverter.convertToDto(cards).sortedBy { card -> card.name }
    }

    fun getOneCard(cardId: Int): CardResponse {
        // TODO: exception handling
        val card = cardRepository.findByIdOrNull(cardId) ?: throw RuntimeException()
        return CardConverter.convertToDto(card)
    }

    @Transactional
    fun addNewCard(request: CreateCardRequest): CardResponse {
        var card = CardConverter.convertToModel(request)
        card = cardRepository.save(card)
        if (request.image != null) {
            // TODO: async
            val url = s3Service.putS3Object(request.image)
            cardRepository.updateImageUrl(card.id!!, url)
        }
        return getOneCard(card.id!!)
    }
}