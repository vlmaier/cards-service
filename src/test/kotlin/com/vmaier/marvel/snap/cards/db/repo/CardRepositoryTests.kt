package com.vmaier.marvel.snap.cards.db.repo

import com.vmaier.marvel.snap.cards.db.model.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val cardRepository: CardRepository) {

    @Test
    fun `When findByIdOrNull then return card`() {
        val yellowjacket = Card("Yellowjacket", 0, 2, "On Reveal: Afflict your other cards at this location with -1 Power")
        val theHood = Card("The Hood", 1, -2, "On Reveal: Add a Demon to your hand")
        val antMan = Card("Ant-Man", 1, 1, "Ongoing: If you have 3 other cards here, +3 Power")
        entityManager.persist(yellowjacket)
        entityManager.persist(theHood)
        entityManager.persist(antMan)
        entityManager.flush()
        val found = cardRepository.findByIdOrNull(yellowjacket.id!!)
        assertThat(found).isEqualTo(yellowjacket)
    }

    @Test
    fun `When findAll then return all cards`() {
        val yellowjacket = Card("Yellowjacket", 0, 2, "On Reveal: Afflict your other cards at this location with -1 Power")
        val theHood = Card("The Hood", 1, -2, "On Reveal: Add a Demon to your hand")
        val antMan = Card("Ant-Man", 1, 1, "Ongoing: If you have 3 other cards here, +3 Power")
        entityManager.persist(yellowjacket)
        entityManager.persist(theHood)
        entityManager.persist(antMan)
        entityManager.flush()
        val found = cardRepository.findAll()
        assertThat(found.contains(yellowjacket)).isTrue
        assertThat(found.contains(theHood)).isTrue
        assertThat(found.contains(antMan)).isTrue
    }
}