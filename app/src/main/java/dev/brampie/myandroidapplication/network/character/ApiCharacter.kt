package dev.brampie.myandroidapplication.network.character

import kotlinx.serialization.Serializable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dev.brampie.myandroidapplication.model.character.Character

/**
 * Represents a character retrieved from the API.
 *
 * @property id The unique identifier of the character.
 * @property name The name of the character.
 * @property status The status of the character (e.g., "Alive," "Dead," or "Unknown").
 * @property species The species of the character. Default is an empty string.
 * @property gender The gender of the character.
 * @property hair The hair description of the character.
 * @property origin The origin of the character.
 * @property abilities A list of abilities or traits possessed by the character.
 * @property img_url The URL to the character's image.
 */
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

/**
 * Converts a list of [ApiCharacter] objects to a list of [Character] objects.
 *
 * @receiver The list of [ApiCharacter] objects to convert.
 * @return A list of [Character] objects.
 */
fun List<ApiCharacter>.asDomainObjects(): List<Character> {
    return map {
        Character(
            id = it.id,
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

/**
 * Converts a list of [ApiCharacter] objects to a list of [Character] objects.
 *
 * @receiver The list of [ApiCharacter] objects to convert.
 * @return A list of [Character] objects.
 */
fun Flow<List<ApiCharacter>>.asDomainObjects(): Flow<List<Character>> {
    return map { it.asDomainObjects() }
}

/**
 * Converts an [ApiCharacter] object to a [Character] object.
 *
 * @receiver The [ApiCharacter] object to convert.
 * @return A [Character] object.
 */
fun ApiCharacter.asDomainObject(): Character {
    return Character(
        id = id,
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

/**
 * Converts a [Flow] of [ApiCharacter] objects to a [Flow] of [Character] objects.
 *
 * @receiver The [Flow] of [ApiCharacter] objects to convert.
 * @return A [Flow] of [Character] objects.
 */
fun Flow<ApiCharacter>.asDomainObject(): Flow<Character> {
    return map { it.asDomainObject() }
}
