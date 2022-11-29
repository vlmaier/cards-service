package com.vmaier.marvel.snap.cards

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
@RequestMapping("/")
class CardsController {

    @GetMapping
    fun getCards(model: Model): String {
        model["title"] = "Marvel SNAP cards"
        model["cards"] = listOf(
            Card("Yellowjacket", 0, 2, "On Reveal: Afflict your other cards at this location with -1 Power"),
            Card("The Hood", 1, -2, "On Reveal: Add a Demon to your hand"),
            Card("Ant-Man", 1, 1, "Ongoing: If you have 3 other cards here, +3 Power")
        ).sortedBy { card -> card.name }
        return "cards"
    }
}