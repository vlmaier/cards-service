package com.vmaier.marvel.snap.cards.service

import com.vmaier.marvel.snap.cards.db.dao.Card
import com.vmaier.marvel.snap.cards.db.repo.CardRepository
import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.dto.CreateCardDTO
import com.vmaier.marvel.snap.cards.openapi.model.CreateCardRequest
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardsService constructor(
    private val cardRepository: CardRepository,
    private val s3Service: S3Service) {

    fun getAllCards(page: Pageable): Page<Card> {
        // TODO: input validation
        return cardRepository.findAll(page)
    }

    fun getOneCard(cardId: Int): Card {
        // TODO: exception handling
        return cardRepository.findByIdOrNull(cardId) ?: throw RuntimeException()
    }

    @Transactional
    fun addNewCard(request: CreateCardDTO): Card {
        var card = CardConverter.convertToDao(request)
        card = cardRepository.save(card)
        if (request.image != null) {
            // TODO: async
            val url = s3Service.putS3Object(request.image)
            cardRepository.updateImageUrl(card.id!!, url)
        }
        return getOneCard(card.id!!)
    }

    @Transactional
    fun addNewCard(request: CreateCardRequest): Card {
        val card = CardConverter.convertToDao(request)
        return cardRepository.save(card)
    }

    @Transactional
    fun removeAllCards() {
        cardRepository.deleteAll()
    }
}