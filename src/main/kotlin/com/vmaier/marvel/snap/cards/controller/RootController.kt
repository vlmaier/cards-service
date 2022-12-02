package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.service.CardsService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootController constructor(private val cardsService: CardsService) {

    @GetMapping("/")
    fun listCards(model: Model): String {
        model["title"] = "Marvel SNAP cards"
        model["cards"] = cardsService.getAllCards()
        return "cards"
    }
}