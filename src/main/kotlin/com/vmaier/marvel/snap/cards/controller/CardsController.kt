package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.dto.CardDto
import com.vmaier.marvel.snap.cards.db.repo.CardRepository
import com.vmaier.marvel.snap.cards.dto.CardConverter
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Controller
class CardsController(private val cardRepository: CardRepository) {

    @GetMapping("/", "/cards")
    fun listCards(model: Model): String {
        model["title"] = "Marvel SNAP cards"
        model["cards"] = CardConverter.convertToDto(
            cardRepository.findAll().toList()
        ).sortedBy { card -> card.name }
        return "cards"
    }

    @PostMapping("/cards")
    fun addCard(@RequestBody cardDto: CardDto, model: Model) {
        val card = CardConverter.convertToModel(cardDto)
        cardRepository.save(card)
    }
}