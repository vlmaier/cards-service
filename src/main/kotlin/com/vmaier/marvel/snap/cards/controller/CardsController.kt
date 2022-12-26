package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.Constants
import com.vmaier.marvel.snap.cards.db.dao.Card
import com.vmaier.marvel.snap.cards.dto.CreateCardDTO
import com.vmaier.marvel.snap.cards.service.CardsService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class CardsController constructor(private val cardsService: CardsService) {

    @GetMapping("cards")
    fun listCardsAsTable(
        model: Model,
        @RequestParam(value = "page", required = false) page: Int?,
        @RequestParam(value = "size", required = false) size: Int?,
        @RequestParam(value = "keyword", required = false) keyword: String?
    ): String {
        val currentPage = page ?: 1
        var pageSize = size ?: Constants.DEFAULT_PAGE_SIZE
        if (pageSize > Constants.MAX_PAGE_SIZE) {
            pageSize = Constants.MAX_PAGE_SIZE
        }
        val cardPage = cardsService.getAllCardsByKeyword(PageRequest.of(currentPage - 1, pageSize, Sort.by("name").ascending()), keyword)
        model.addAttribute("keyword", keyword)
        model.addAttribute("cardPage", cardPage)
        return "cards"
    }

    @GetMapping("card-grid")
    fun listCardsAsGrid(
        model: Model,
        @RequestParam(value = "keyword", required = false) keyword: String?,
        @RequestParam(value = "cost", required = false) cost: Int?,
        @RequestParam(value = "power", required = false) power: Int?,
        @RequestParam(value = "series", required = false) series: String?,
        @RequestParam(value = "isOwned", required = false) isOwned: Boolean?,
    ): String {
        val cards = cardsService.getAllCardsByKeyword(keyword, cost, power, series, isOwned).sortedBy { card -> card.name }
        val costValues = cards.map { card: Card -> card.cost }.toSet().sorted()
        val powerValues = cards.map { card: Card -> card.power }.toSet().sorted()
        val seriesValues = cards.map { card: Card -> card.series }.toSet().sortedBy { it }
        model.addAttribute("keyword", keyword)
        model.addAttribute("cost", cost)
        model.addAttribute("power", power)
        model.addAttribute("series", series)
        model.addAttribute("isOwned", isOwned)
        model.addAttribute("costValues", costValues)
        model.addAttribute("powerValues", powerValues)
        model.addAttribute("seriesValues", seriesValues)
        model.addAttribute("cards", cards)
        return "card-grid"
    }

    @GetMapping("cards/{cardId}")
    fun findCard(model: Model, @PathVariable("cardId") cardId: Int): String {
        model.addAttribute("card", cardsService.getOneCard(cardId))
        return "card"
    }

    @GetMapping("new-card")
    fun getCreateCard(model: Model): String {
        model.addAttribute("request", CreateCardDTO())
        return "new-card"
    }

    @PostMapping("new-card")
    fun addCard(model: Model, request: CreateCardDTO): String {
        // TODO: input validation
        val newCard = cardsService.addNewCard(request)
        return "redirect:/cards/" + newCard.id
    }

    @DeleteMapping("cards")
    fun removeCards(model: Model): String {
        cardsService.removeAllCards()
        return "redirect:/card-grid"
    }

    @DeleteMapping("cards/{cardId}")
    fun removeCard(model: Model, @PathVariable("cardId") cardId: Int): String {
        cardsService.removeAllCard(cardId)
        return "redirect:/cards$cardId"
    }
}