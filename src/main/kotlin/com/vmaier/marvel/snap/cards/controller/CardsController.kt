package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.dto.CreateCardRequest
import com.vmaier.marvel.snap.cards.service.CardsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class CardsController constructor(private val cardsService: CardsService) {

    @GetMapping("cards")
    fun listCards(model: Model): String {
        model.addAttribute("cards", cardsService.getAllCards())
        return "cards"
    }

    @GetMapping("cards/{cardId}")
    fun findCard(@PathVariable("cardId") cardId: Int, model: Model): String {
        model.addAttribute("card", cardsService.getOneCard(cardId))
        return "card"
    }

    @GetMapping("new-card")
    fun getCreateCardPage(model: Model): String {
        model.addAttribute("request", CreateCardRequest())
        return "new-card"
    }

    @PostMapping("new-card")
    fun addCard(request: CreateCardRequest, model: Model): String {
        // TODO: input validation
        val newCard = cardsService.addNewCard(request)
        return "redirect:/cards/" + newCard.id
    }
}