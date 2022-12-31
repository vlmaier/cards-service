package com.vmaier.marvel.snap.cards.openapi.api

import com.vmaier.marvel.snap.cards.openapi.model.CardListResponse
import com.vmaier.marvel.snap.cards.openapi.model.CardResponse
import com.vmaier.marvel.snap.cards.openapi.model.CreateCardRequest
import com.vmaier.marvel.snap.cards.openapi.model.ErrorResponse
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
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "cards")
@RequestMapping("/v1/cards", produces = ["application/json"])
interface CardsApi {

    @Operation(summary = "List all available cards", description = "/openapi/list-cards.md")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "OK", content = [
                    Content(schema = Schema(implementation = CardListResponse::class))
                ]
            ),
            ApiResponse(
                responseCode = "400", description = "Bad Request", content = [
                    Content(schema = Schema(implementation = ErrorResponse::class))
                ]
            )
        ]
    )
    @ResponseBody
    @Parameter(
        `in` = ParameterIn.QUERY,
        description = "Search for a keyword in cards (name or ability). "
                + "Maximum length allowed: `128` characters. ",
        name = "keyword",
        schema = Schema(type = "string")
    )
    @Parameter(
        `in` = ParameterIn.QUERY,
        description = "Zero-based page index `(0..N)`",
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
        description = "Sorting criteria in the format: `property,(asc|desc)`. "
                + "Default sort order is ascending. "
                + "Multiple sort criteria are supported. ",
        name = "sort",
        array = ArraySchema(schema = Schema(type = "string"))
    )
    @GetMapping
    fun listCards(@Parameter(hidden = true) page: Pageable, keyword: String?): ResponseEntity<CardListResponse>

    @Operation(summary = "Find card", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "OK", content = [
                    Content(schema = Schema(implementation = CardResponse::class))
                ]
            ),
            ApiResponse(
                responseCode = "400", description = "Bad Request", content = [
                    Content(schema = Schema(implementation = ErrorResponse::class))
                ]
            ),
            ApiResponse(
                responseCode = "404", description = "Not Found", content = [
                    Content(schema = Schema(implementation = ErrorResponse::class))
                ]
            )
        ]
    )
    @ResponseBody
    @GetMapping("{cardId}")
    fun findCard(@PathVariable("cardId") cardId: Int): ResponseEntity<CardResponse>

    @Operation(summary = "Add new card", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "Created", content = [
                    Content(schema = Schema(implementation = CardResponse::class))
                ]
            ),
            ApiResponse(
                responseCode = "400", description = "Bad Request", content = [
                    Content(schema = Schema(implementation = ErrorResponse::class))
                ]
            )
        ]
    )
    @ResponseBody
    @PostMapping(consumes = ["application/json"])
    fun addCard(@RequestBody request: CreateCardRequest): ResponseEntity<CardResponse>

    @Operation(summary = "Remove all cards", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "No Content")
        ]
    )
    @DeleteMapping
    fun removeCards(): ResponseEntity<Void>

    @Operation(summary = "Add card to collection", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "No Content")
        ]
    )
    @PostMapping("{cardId}/collection")
    fun addToCollection(@PathVariable("cardId") cardId: Int): ResponseEntity<Void>

    @Operation(summary = "Remove card from collection", description = "TODO ...")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "No Content")
        ]
    )
    @DeleteMapping("{cardId}/collection")
    fun removeFromCollection(@PathVariable("cardId") cardId: Int): ResponseEntity<Void>
}