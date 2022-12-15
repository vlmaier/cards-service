package com.vmaier.marvel.snap.cards.db.repo

import com.vmaier.marvel.snap.cards.db.dao.Card
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface CardRepository: CrudRepository<Card, Int> {

    fun findAll(pageable: Pageable): Page<Card>

    fun findAllByNameContainingIgnoreCaseOrAbilityContainingIgnoreCase(name: String, ability: String): Iterable<Card>

    fun findAllByNameContainingIgnoreCaseOrAbilityContainingIgnoreCase(pageable: Pageable, name: String, ability: String): Page<Card>

    @Modifying
    @Query("UPDATE Card c SET c.url = :url WHERE c.id = :id")
    fun updateImageUrl(@Param(value = "id") id: Int, @Param(value = "url") url: String)
}