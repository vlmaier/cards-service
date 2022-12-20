package com.vmaier.marvel.snap.cards.error

class ValidationException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}