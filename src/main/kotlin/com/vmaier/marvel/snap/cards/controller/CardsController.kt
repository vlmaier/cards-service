package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.Constants
import com.vmaier.marvel.snap.cards.dto.CreateCardDTO
import com.vmaier.marvel.snap.cards.service.CardsService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
class CardsController constructor(private val cardsService: CardsService) {

    @GetMapping("cards")
    fun listCards(model: Model,
                  @RequestParam("page") page: Optional<Int>,
                  @RequestParam("size") size: Optional<Int>
    ): String {
        val currentPage = page.orElse(1)
        val pageSize = size.orElse(Constants.PAGE_SIZE)
        val cardPage = cardsService.getAllCards(PageRequest.of(currentPage - 1, pageSize))
        model.addAttribute("cardPage", cardPage)
        return "cards"
    }

    @GetMapping("cards/{cardId}")
    fun findCard(@PathVariable("cardId") cardId: Int, model: Model): String {
        model.addAttribute("card", cardsService.getOneCard(cardId))
        return "card"
    }

    @GetMapping("new-card")
    fun getCreateCard(model: Model): String {
        model.addAttribute("request", CreateCardDTO())
        return "new-card"
    }

    @PostMapping("new-card")
    fun addCard(request: CreateCardDTO, model: Model): String {
        // TODO: input validation
        val newCard = cardsService.addNewCard(request)
        return "redirect:/cards/" + newCard.id
    }

    @DeleteMapping("cards")
    fun removeCards(model: Model): String {
        cardsService.removeAllCards()
        return "redirect:/cards"
    }
}