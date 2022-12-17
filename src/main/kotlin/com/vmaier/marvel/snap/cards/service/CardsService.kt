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
import java.util.*
import kotlin.math.cos

@Service
class CardsService constructor(
    private val cardRepository: CardRepository,
    private val s3Service: S3Service) {

    fun getOneCard(cardId: Int): Card {
        return cardRepository.findByIdOrNull(cardId) ?: throw NotFoundException("Card with id: $cardId not found.")
    }

    fun getAllCardsByKeyword(page: Pageable, keyword: String?): Page<Card> {
        // TODO: input validation
        return if (keyword.isNullOrEmpty()) {
            cardRepository.findAll(page)
        } else {
            cardRepository.findAllByKeyword(page, keyword)
        }
    }

    fun getAllCardsByKeyword(keyword: Optional<String>, cost: Optional<String>, power: Optional<String>): Iterable<Card> {
        var cards = if (keyword.isEmpty) {
            cardRepository.findAll()
        } else {
            cardRepository.findAllByKeyword(keyword.get())
        }
        if (cost.isPresent) {
            cards = cards.filter {
                card -> card.cost == cost.get().toInt()
            }
        }
        if (power.isPresent) {
            cards = cards.filter {
                card -> card.power == power.get().toInt()
            }
        }
        return cards
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