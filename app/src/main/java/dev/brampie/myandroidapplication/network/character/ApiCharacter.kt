package dev.brampie.myandroidapplication.network.character

import kotlinx.serialization.Serializable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dev.brampie.myandroidapplication.model.character.Character

@Serializable
data class ApiCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val origin: String,
    val img_url: String
)

@Serializable
data class ApiCharacterResponse(
    val results: List<ApiCharacter>
)

fun List<ApiCharacter>.asDomainObjects(): List<Character> {
    return map {
        Character(
            externalId = it.id,
            name = it.name,
            status = it.status,
            origin = it.origin,
            img_url = it.img_url
        )
    }
}

fun Flow<List<ApiCharacter>>.asDomainObjects(): Flow<List<Character>> {
    return map { it.asDomainObjects() }
}
