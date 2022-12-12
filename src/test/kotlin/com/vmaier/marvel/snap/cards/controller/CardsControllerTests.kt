package com.vmaier.marvel.snap.cards.controller

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CardsControllerTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `List cards page loads`() {
        val entity = restTemplate.getForEntity<String>("/cards")
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).contains("<title>All cards</title>")
    }

    @Test
    fun `Add new card page loads`() {
        val entity = restTemplate.getForEntity<String>("/new-card")
        Assertions.assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity.body).contains("<title>Add new card</title>")
    }
}