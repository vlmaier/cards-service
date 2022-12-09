package com.vmaier.marvel.snap.cards.controller.api

import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.openapi.CardResponse
import com.vmaier.marvel.snap.cards.openapi.CreateCardRequest
import com.vmaier.marvel.snap.cards.service.CardsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springdoc.core.converters.models.PageableAsQueryParam
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/v1/cards", produces = ["application/json"])
class CardsApiController constructor(private val cardsService: CardsService) {

    @Operation(summary = "List all available cards", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @ResponseBody
    @PageableAsQueryParam
    @GetMapping
    fun listCards(@Parameter(hidden = true) page: Pageable): ResponseEntity<List<CardResponse>> {
        val cardPage = cardsService.getAllCards(page)
        val response = CardConverter.convertToModel(cardPage.toList())
        return ResponseEntity<List<CardResponse>>(response, HttpStatus.OK)
    }

    @Operation(summary = "Find card", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @ResponseBody
    @GetMapping("{cardId}")
    fun findCard(@PathVariable("cardId") cardId: Int): ResponseEntity<CardResponse> {
        val card = cardsService.getOneCard(cardId)
        val response = CardConverter.convertToModel(card)
        return ResponseEntity<CardResponse>(response, HttpStatus.OK)
    }

    @Operation(summary = "Add new card", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Created")
        ]
    )
    @ResponseBody
    @PostMapping(consumes = ["application/json"])
    fun addCard(@RequestBody request: CreateCardRequest): ResponseEntity<CardResponse> {
        val newCard = cardsService.addNewCard(request)
        val response = CardConverter.convertToModel(newCard)
        return ResponseEntity<CardResponse>(response, HttpStatus.CREATED)
    }

    @Operation(summary = "Remove all cards", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "No Content")
        ]
    )
    @DeleteMapping
    fun removeCards(): ResponseEntity<Void> {
        cardsService.removeAllCards()
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}