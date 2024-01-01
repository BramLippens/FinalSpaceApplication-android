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
    val species: String = "",
    val gender: String,
    val hair: String,
    val origin: String,
    val abilities: List<String>,
    val img_url: String
)

fun List<ApiCharacter>.asDomainObjects(): List<Character> {
    return map {
        Character(
            externalId = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            gender = it.gender,
            hair = it.hair,
            origin = it.origin,
            abilities = it.abilities,
            img_url = it.img_url
        )
    }
}

fun Flow<List<ApiCharacter>>.asDomainObjects(): Flow<List<Character>> {
    return map { it.asDomainObjects() }
}

fun ApiCharacter.asDomainObject(): Character {
    return Character(
        externalId = id,
        name = name,
        status = status,
        species = species?:"",
        gender = gender,
        hair = hair,
        origin = origin,
        abilities = abilities,
        img_url = img_url
    )
}

fun Flow<ApiCharacter>.asDomainObject(): Flow<Character> {
    return map { it.asDomainObject() }
}
