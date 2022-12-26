package com.vmaier.marvel.snap.cards.utils

import com.vmaier.marvel.snap.cards.error.ValidationException
import java.net.MalformedURLException
import java.net.URISyntaxException
import java.net.URL

object Validator {

    fun checkIfKeywordIsValid(keyword: String?) {
        if (keyword.isNullOrEmpty()) {
            return
        }
        if (keyword.length > 128) {
            throw ValidationException("keyword is too long (max. 128 characters).")
        }
    }

    fun checkIfCardIdIsValid(cardId: Int) {
        if (cardId > 0) {
            return
        }
        throw ValidationException("cardId must be positive.")
    }

    fun checkIfNameIsValid(name: String?) {
        if (name.isNullOrEmpty()) {
            throw ValidationException("name is required and therefore cannot be null or empty.")
        }
        if (name.length <= 255) {
            return
        }
        throw ValidationException("name is too long (max. 255 characters).")
    }

    fun checkIfCostIsValid(cost: Int?) {
        if (cost == null) {
            throw ValidationException("cost is required and therefore cannot be null.")
        }
        if (cost >= 0) {
            return
        }
        throw ValidationException("cost must be positive or 0.")
    }

    fun checkIfPowerIsValid(power: Int?) {
        if (power == null) {
            throw ValidationException("power is required and therefore cannot be null.")
        }
        if (power >= -20 && power <= 20) {
            return
        }
        throw ValidationException("power must be between -20 and 20")
    }

    fun checkIfAbilityIsValid(ability: String?) {
        if (ability.isNullOrEmpty()) {
            return
        }
        if (ability.length <= 1024) {
            return
        }
        throw ValidationException("ability is too long (max. 1024 characters).")
    }

    fun checkIfSeriesIsValid(series: String?) {
        if (series.isNullOrEmpty()) {
            return
        }
        if (series.length <= 255) {
            return
        }
        throw ValidationException("series is too long (max. 255 characters).")
    }

    fun checkIfImageUrlIsValid(imageUrl: String?) {
        if (imageUrl.isNullOrEmpty()) {
            throw ValidationException("imageUrl is required and therefore cannot be null or empty.")
        }
        if (imageUrl.length >= 255) {
            throw ValidationException("imageUrl is too long (max. 255 characters).")
        }
        if (!isValidURL(imageUrl)) {
            throw ValidationException("imageUrl is invalid.")
        }
    }

    private fun isValidURL(url: String?): Boolean {
        try {
            URL(url).toURI()
        } catch (e: MalformedURLException) {
            return false
        } catch (e: URISyntaxException) {
            return false
        }
        return true
    }
}