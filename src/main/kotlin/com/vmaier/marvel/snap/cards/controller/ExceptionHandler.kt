package com.vmaier.marvel.snap.cards.controller

import com.vmaier.marvel.snap.cards.openapi.model.ErrorResponse
import com.vmaier.marvel.snap.cards.service.NotFoundException
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
}