package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.dto.CreateCardRequest
import com.vmaier.marvel.snap.cards.service.CardsService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

@Controller
class CardsController constructor(private val cardsService: CardsService) {

    @GetMapping("cards")
    fun listCards(model: Model,
                  @RequestParam("page") page: Optional<Int>,
                  @RequestParam("size") size: Optional<Int>
    ): String {
        val currentPage = page.orElse(1)
        val pageSize = size.orElse(10)
        val cardPage = cardsService.getAllCards(PageRequest.of(currentPage - 1, pageSize))
        model.addAttribute("cardPage", cardPage)
        val totalPages = cardPage.totalPages
        if (totalPages > 0) {
            val pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList())
            model.addAttribute("pageNumbers", pageNumbers)
        }
        return "cards"
    }

    @GetMapping("cards/{cardId}")
    fun findCard(@PathVariable("cardId") cardId: Int, model: Model): String {
        model.addAttribute("card", cardsService.getOneCard(cardId))
        return "card"
    }

    @GetMapping("new-card")
    fun getCreateCard(model: Model): String {
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