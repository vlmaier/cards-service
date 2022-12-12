package com.vmaier.marvel.snap.cards.openapi

import io.swagger.v3.oas.models.Operation
import mu.KotlinLogging
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Component
class ApiDescriptionPlugin : OperationCustomizer {

    private val logger = KotlinLogging.logger {}

    override fun customize(api: Operation, handlerMethod: HandlerMethod): Operation {
        if (api.description.isNullOrEmpty()) {
            api.description = "Documentation not found."
            return api
        }
        try {
            val foundDescription = Files.readString(
                Paths.get(
                    Objects.requireNonNull(javaClass.getResource(api.description)).toURI()
                )
            )
            api.description = foundDescription
        } catch (e: Exception) {
            logger.warn { "Documentation file for ${api.operationId} API not found." }
        }
        return api
    }
}