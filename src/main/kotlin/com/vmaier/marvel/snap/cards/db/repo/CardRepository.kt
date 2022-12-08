package com.vmaier.marvel.snap.cards.db.repo

import com.vmaier.marvel.snap.cards.db.model.Card
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface CardRepository: CrudRepository<Card, Int> {

    @Modifying
    @Query("UPDATE Card c SET c.url = :url WHERE c.id = :id")
    fun updateImageUrl(@Param(value = "id") id: Int, @Param(value = "url") url: String)
}