package com.vmaier.marvel.snap.cards.error

import com.vmaier.marvel.snap.cards.openapi.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.NOT_FOUND
        val response = ErrorResponse(httpStatus.value(), ex.message)
        return ResponseEntity(response, httpStatus)
    }

    @ExceptionHandler
    fun handleValidationException(ex: ValidationException): ResponseEntity<ErrorResponse> {
        val httpStatus = HttpStatus.BAD_REQUEST
        val response = ErrorResponse(httpStatus.value(), ex.message)
        return ResponseEntity(response, httpStatus)
    }
}