package com.vmaier.marvel.snap.cards.db.repo

import com.vmaier.marvel.snap.cards.db.model.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository: CrudRepository<Card, Int>