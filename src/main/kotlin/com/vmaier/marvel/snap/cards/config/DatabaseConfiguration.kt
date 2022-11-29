package com.vmaier.marvel.snap.cards.config

import com.vmaier.marvel.snap.cards.model.Card
import com.vmaier.marvel.snap.cards.repo.CardRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfiguration {

    @Bean
    fun databaseInitializer(cardRepository: CardRepository) = ApplicationRunner {
        val cards = listOf(
            Card("Yellowjacket", 0, 2, "On Reveal: Afflict your other cards at this location with -1 Power"),
            Card("The Hood", 1, -2, "On Reveal: Add a Demon to your hand"),
            Card("Ant-Man", 1, 1, "Ongoing: If you have 3 other cards here, +3 Power")
        )
        cardRepository.saveAll(cards)
    }
}