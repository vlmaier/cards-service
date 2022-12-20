package com.vmaier.marvel.snap.cards.utils

import com.vmaier.marvel.snap.cards.error.ValidationException

object Validator {

    fun checkIfKeywordIsTooLong(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            return
        }
        if (keyword.length > 128) {
            throw ValidationException("keyword is too long (max. 128 characters)")
        }
    }

    fun checkIfCardIdIsValid(cardId: Int) {
        if (cardId > 0) {
            return
        }
        throw ValidationException("cardId must be positive")
    }
}