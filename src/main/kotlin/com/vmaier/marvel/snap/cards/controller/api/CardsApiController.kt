package com.vmaier.marvel.snap.cards.controller.api

import com.vmaier.marvel.snap.cards.Constants
import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.openapi.api.CardsApi
import com.vmaier.marvel.snap.cards.openapi.model.CardResponse
import com.vmaier.marvel.snap.cards.openapi.model.CreateCardRequest
import com.vmaier.marvel.snap.cards.service.CardsService
import com.vmaier.marvel.snap.cards.utils.Validator
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CardsApiController constructor(private val cardsService: CardsService) : CardsApi {

    override fun listCards(page: Pageable, keyword: String?): ResponseEntity<List<CardResponse>> {
        Validator.checkIfKeywordIsValid(keyword)
        val pageRequest = if (page.pageSize > Constants.MAX_PAGE_SIZE) {
            PageRequest.of(page.pageNumber, Constants.MAX_PAGE_SIZE, page.sort)
        } else {
            PageRequest.of(page.pageNumber, page.pageSize, page.sort)
        }
        val cardPage = cardsService.getAllCardsByKeyword(pageRequest, keyword)
        val response = CardConverter.convertToModel(cardPage.toList())
        return ResponseEntity<List<CardResponse>>(response, HttpStatus.OK)
    }

    override fun findCard(cardId: Int): ResponseEntity<CardResponse> {
        Validator.checkIfCardIdIsValid(cardId)
        val card = cardsService.getOneCard(cardId)
        val response = CardConverter.convertToModel(card)
        return ResponseEntity<CardResponse>(response, HttpStatus.OK)
    }

    override fun addCard(request: CreateCardRequest): ResponseEntity<CardResponse> {
        Validator.checkIfNameIsValid(request.name)
        Validator.checkIfCostIsValid(request.cost)
        Validator.checkIfPowerIsValid(request.power)
        Validator.checkIfAbilityIsValid(request.ability)
        Validator.checkIfImageUrlIsValid(request.imageUrl)
        val newCard = cardsService.addNewCard(request)
        val response = CardConverter.convertToModel(newCard)
        return ResponseEntity<CardResponse>(response, HttpStatus.CREATED)
    }

    override fun removeCards(): ResponseEntity<Void> {
        cardsService.removeAllCards()
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    override fun addToCollection(cardId: Int): ResponseEntity<Void> {
        Validator.checkIfCardIdIsValid(cardId)
        cardsService.addToCollection(cardId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    override fun removeFromCollection(cardId: Int): ResponseEntity<Void> {
        Validator.checkIfCardIdIsValid(cardId)
        cardsService.removeFromCollection(cardId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}