package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.model.Card
import com.vmaier.marvel.snap.cards.repo.CardRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

@Controller
class CardsController(private val cardRepository: CardRepository) {

    @GetMapping("/", "/cards")
    fun listCards(model: Model): String {
        model["title"] = "Marvel SNAP cards"
        model["cards"] = cardRepository.findAll().sortedBy { card -> card.name }
        return "cards"
    }

    @PostMapping("/cards")
    fun addCard(card: Card, model: Model) {
        cardRepository.save(card)
    }
}