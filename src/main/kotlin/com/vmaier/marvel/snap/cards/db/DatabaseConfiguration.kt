package com.vmaier.marvel.snap.cards.db

import com.vmaier.marvel.snap.cards.db.model.Card
import com.vmaier.marvel.snap.cards.db.repo.CardRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfiguration {

    @Bean
    fun databaseInitializer(cardRepository: CardRepository) = ApplicationRunner {
        val cards = listOf(
            Card("Yellowjacket", 0, 2, "On Reveal: Afflict your other cards at this location with -1 Power", 1),
            Card("The Hood", 1, -2, "On Reveal: Add a Demon to your hand", 2),
            Card("Ant-Man", 1, 1, "Ongoing: If you have 3 other cards here, +3 Power", 3)
        )
        cardRepository.saveAll(cards)
    }
}