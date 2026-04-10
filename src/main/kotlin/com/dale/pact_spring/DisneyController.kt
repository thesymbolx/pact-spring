package com.dale.pact_spring

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

class DisneyController {
    data class DisneyCharacter(
        @JsonProperty("_id") val id: Int,
        val name: String,
        val imageUrl: String
    )

    @RestController
    @RequestMapping("/character")
    class DisneyController {
        private val stubbedDatabase = mapOf(
            112 to DisneyCharacter(112, "Mickey Mouse", "https://example.com/mickey.png"),
            113 to DisneyCharacter(113, "Donald Duck", "https://example.com/donald.png"),
            114 to DisneyCharacter(114, "Goofy", "https://example.com/goofy.png")
        )

        @GetMapping
        fun getAllCharacters(): Map<String, List<DisneyCharacter>> {
            return mapOf("data" to stubbedDatabase.values.toList())
        }

        @GetMapping("/{id}")
        fun getCharacter(@PathVariable id: Int): ResponseEntity<DisneyCharacter> {
            val character = stubbedDatabase[id]

            return if (character != null) {
                ResponseEntity.ok(character)
            } else {
                ResponseEntity.notFound().build()
            }
        }
    }
}