package com.vmaier.marvel.snap.cards.db.repo

import com.vmaier.marvel.snap.cards.db.dao.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import java.util.*
import kotlin.random.Random

@DataJpaTest
class CardRepositoryTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val cardRepository: CardRepository) {

    var testData: List<Card> = listOf()

    @BeforeEach
    internal fun init() {
        testData = listOf(
            Card("Abomination", 5, 9, null, "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/abomination.webp"),
            Card("Absorbing Man", 4, 3, "On reveal: If the last card you played has an on reveal ability, this card copies it.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/absorbing-man.webp"),
            Card("Adam Warlock", 2, 0, "At the end of each turn, if you are winning this location, draw a card.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/absorbing-man.webp"),
            Card("Aero", 5, 8, "On reveal: Move all enemy cards played this turn to this location", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/aero.webp"),
            Card("Agatha Harkness", 6, 14, "Agatha starts in your hand and plays your cards for you.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/agatha-harkness.webp"),
            Card("Agent 13", 1, 2, "On reveal: Add a random card to your hand", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/agent-13.webp"),
            Card("Agent Coulson", 3, 4, "Add a random 4-cost and 5-cost card to your hand.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/agent-coulson.webp"),
            Card("America Chavez", 6, 9, "You always draw this card on turn 6, and not before.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/america-chavez.webp"),
            Card("Angel", 1, 2, "When one of your cards is destroyed, this flies out of your deck to replace it.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/angel.webp"),
            Card("Angela", 2, 0, "When you play a card here, +2 power.", "https://marvelsnapzone.com/wp-content/themes/blocksy-child/assets/media/cards/angela.webp")
        )
        for (card in testData) {
            entityManager.persist(card)
        }
        entityManager.flush()
    }

    @AfterEach
    internal fun destroy() {
        for (card in testData) {
            entityManager.remove(entityManager.merge(card))
        }
        entityManager.flush()
    }

    @Test
    fun `When findByIdOrNull then return card`() {
        val id = Random(Date().time).nextInt(testData.first().id!!, testData.last().id!!)
        val found = cardRepository.findByIdOrNull(id)
        assertThat(testData.contains(found)).isTrue
    }

    @Test
    fun `When findAll then return all cards`() {
        val found = cardRepository.findAll()
        for (card in found) {
            assertThat(found.contains(card)).isTrue
        }
    }

    @Test
    fun `Update image URL on a card`() {
        val card = testData[Random(Date().time).nextInt(testData.indexOf(testData.first()), testData.lastIndex)]
        val newUrl = "https://test/image/test.jpg"
        cardRepository.updateImageUrl(card.id!!, newUrl)
        val found = cardRepository.findByIdOrNull(card.id!!)
        assertThat(found?.url.equals(newUrl))
    }

    @Test
    fun `When deleteAll then return no cards`() {
        cardRepository.deleteAll()
        val found = cardRepository.findAll()
        assertThat(found.toList().isEmpty()).isTrue
    }
}