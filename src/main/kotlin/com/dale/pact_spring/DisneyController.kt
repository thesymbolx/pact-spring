package com.dale.pact_spring

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/character")
class DisneyController(private val repository: DisneyRepository) {

    @GetMapping
    fun getAllCharacters(): Map<String, List<DisneyCharacter>> {
        // Use our new getCharacters() method
        return mapOf("data" to repository.getCharacters())
    }

    @GetMapping("/{id}")
    fun getCharacter(@PathVariable id: Int): ResponseEntity<DisneyCharacter> {
        // Use our new getCharacter() method
        val character = repository.getCharacter(id)

        return if (character != null) {
            ResponseEntity.ok(character)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}