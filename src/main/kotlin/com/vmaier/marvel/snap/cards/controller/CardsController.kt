package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.dto.CardResponse
import com.vmaier.marvel.snap.cards.dto.CreateCardRequest
import com.vmaier.marvel.snap.cards.service.CardsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/cards", produces = ["application/json"])
class CardsController constructor(private val cardsService: CardsService) {

    @Operation(summary = "List all available cards", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "OK")
        ]
    )
    @ResponseBody
    @GetMapping
    fun listCards(): ResponseEntity<List<CardResponse>> {
        val response = cardsService.getAllCards()
        return ResponseEntity<List<CardResponse>>(response, HttpStatus.OK)
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
        // TODO: validation
        val response = cardsService.addNewCard(request)
        return ResponseEntity<CardResponse>(response, HttpStatus.CREATED)
    }
}