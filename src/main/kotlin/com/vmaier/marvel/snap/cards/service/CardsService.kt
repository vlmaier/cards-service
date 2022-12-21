package com.vmaier.marvel.snap.cards.service

import com.vmaier.marvel.snap.cards.db.dao.Card
import com.vmaier.marvel.snap.cards.db.repo.CardRepository
import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.dto.CreateCardDTO
import com.vmaier.marvel.snap.cards.error.NotFoundException
import com.vmaier.marvel.snap.cards.error.ValidationException
import com.vmaier.marvel.snap.cards.openapi.model.CreateCardRequest
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mapping.PropertyReferenceException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional
class CardsService constructor(
    private val cardRepository: CardRepository,
    private val s3Service: S3Service
) {

    private val logger = KotlinLogging.logger {}

    fun getOneCard(cardId: Int): Card {
        return cardRepository.findByIdOrNull(cardId) ?: throw NotFoundException("Card with id: $cardId not found.")
    }

    fun getAllCardsByKeyword(pageRequest: Pageable, keyword: String?): Page<Card> {
        val cards: Page<Card>
        try {
            cards = if (keyword.isNullOrEmpty()) {
                cardRepository.findAll(pageRequest)
            } else {
                cardRepository.findAllByKeyword(pageRequest, keyword)
            }
        } catch (ex: PropertyReferenceException) {
            logger.error { ex.message }
            throw ValidationException(ex.message, ex)
        }
        return cards
    }

    fun getAllCardsByKeyword(keyword: String?, cost: Int?, power: Int?): Iterable<Card> {
        var cards = if (keyword.isNullOrEmpty()) {
            cardRepository.findAll()
        } else {
            cardRepository.findAllByKeyword(keyword)
        }
        if (cost != null) {
            cards = cards.filter { card ->
                card.cost == cost
            }
        }
        if (power != null) {
            cards = cards.filter { card ->
                card.power == power
            }
        }
        return cards
    }

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

    fun addNewCard(request: CreateCardRequest): Card {
        val card = CardConverter.convertToDao(request)
        return cardRepository.save(card)
    }

    fun removeAllCards() {
        cardRepository.deleteAll()
    }
}