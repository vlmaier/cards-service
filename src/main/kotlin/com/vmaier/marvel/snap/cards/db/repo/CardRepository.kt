package com.vmaier.marvel.snap.cards.db.repo

import com.vmaier.marvel.snap.cards.db.dao.Card
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param


interface CardRepository : CrudRepository<Card, Int> {

    fun findAll(pageRequest: Pageable): Page<Card>

    @Query("SELECT c FROM Card c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.ability) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    fun findAllByKeyword(@Param("keyword") keyword: String): Iterable<Card>

    @Query("SELECT c FROM Card c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.ability) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    fun findAllByKeyword(pageRequest: Pageable, @Param("keyword") keyword: String): Page<Card>

    @Modifying
    @Query("UPDATE Card c SET c.url = :url WHERE c.id = :id")
    fun updateImageUrl(@Param("id") id: Int, @Param("url") url: String)

    @Modifying
    @Query("UPDATE Card c SET c.isOwned = :isOwned WHERE c.id = :id")
    fun updateIsOwned(@Param("id") id: Int, @Param("isOwned") isOwned: Boolean)
}