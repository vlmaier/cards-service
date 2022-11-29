package com.vmaier.marvel.snap.cards.repo

import com.vmaier.marvel.snap.cards.model.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository: CrudRepository<Card, Int>