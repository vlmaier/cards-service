package com.vmaier.marvel.snap.cards.controller.api

import com.vmaier.marvel.snap.cards.dto.CardConverter
import com.vmaier.marvel.snap.cards.openapi.model.CardResponse
import com.vmaier.marvel.snap.cards.openapi.model.CreateCardRequest
import com.vmaier.marvel.snap.cards.openapi.model.ErrorResponse
import com.vmaier.marvel.snap.cards.service.CardsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "cards")
@RestController
@RequestMapping("/v1/cards", produces = ["application/json"])
class CardsApiController constructor(private val cardsService: CardsService) {

    @Operation(summary = "List all available cards", description = "/openapi/list-cards.md")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @ResponseBody
    @Parameter(
        `in` = ParameterIn.QUERY,
        description = "Zero-based page index (0..N)",
        name = "page",
        schema = Schema(type = "integer")
    )
    @Parameter(
        `in` = ParameterIn.QUERY,
        description = "The size of the page to be returned",
        name = "size",
        schema = Schema(type = "integer")
    )
    @Parameter(
        `in` = ParameterIn.QUERY,
        description = "Sorting criteria in the format: property,(asc|desc). "
                + "Default sort order is ascending. "
                + "Multiple sort criteria are supported. ",
        name = "sort",
        array = ArraySchema(schema = Schema(type = "string"))
    )
    @Parameter(
        `in` = ParameterIn.QUERY,
        description = "Search for a keyword in cards (name or ability)",
        name = "keyword",
        schema = Schema(type = "string")
    )
    @GetMapping
    fun listCards(@Parameter(hidden = true) page: Pageable, keyword: String?): ResponseEntity<List<CardResponse>> {
        val cardPage = cardsService.getAllCardsByKeyword(page, keyword)
        val response = CardConverter.convertToModel(cardPage.toList())
        return ResponseEntity<List<CardResponse>>(response, HttpStatus.OK)
    }

    @Operation(summary = "Find card", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK", content = [
                Content(schema = Schema(implementation = CardResponse::class))
            ]),
            ApiResponse(responseCode = "404", description = "Not Found", content = [
                Content(schema = Schema(implementation = ErrorResponse::class))
            ])
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