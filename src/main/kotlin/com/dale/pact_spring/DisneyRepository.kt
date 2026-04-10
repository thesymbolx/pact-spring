package com.dale.pact_spring

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Repository

@Repository
class DisneyRepository {
    private val database = mutableMapOf(
        112 to DisneyCharacter(112, "Mickey Mouse", "https://example.com/mickey.png"),
        113 to DisneyCharacter(113, "Donald Duck", "https://example.com/donald.png"),
        114 to DisneyCharacter(114, "Goofy", "https://example.com/goofy.png")
    )

    fun getCharacters(): List<DisneyCharacter> {
        return database.values.toList()
    }

    fun getCharacter(id: Int): DisneyCharacter? {
        return database[id]
    }

    fun clear() {
        database.clear()
    }

    fun save(character: DisneyCharacter) {
        database[character.id] = character
    }

    fun save(vararg characters: DisneyCharacter) {
        characters.forEach { character ->
            database[character.id] = character
        }
    }
}

data class DisneyCharacter(
    @JsonProperty("_id") val id: Int,
    val name: String,
    val imageUrl: String
)
