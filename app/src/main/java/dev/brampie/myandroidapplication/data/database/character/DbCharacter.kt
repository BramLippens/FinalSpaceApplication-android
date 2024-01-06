package dev.brampie.myandroidapplication.data.database.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.character.Character

/**
 * Entity representation of a character for the room database.
 * @property id Unique identifier for the character.
 * @property name Name of the character.
 * @property status Current status (e.g., alive, dead).
 * @property species Species of the character.
 * @property gender Gender of the character.
 * @property hair Hair description.
 * @property origin Character's origin.
 * @property abilities List of character's abilities.
 * @property img_url URL to the character's image.
 */
@Entity(tableName = "characters")
data class DbCharacter(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val hair: String,
    val origin: String,
    val abilities: String,
    val img_url: String
)

/**
 * Converts a database character to a domain character.
 * @return A domain model of character.
 */
fun DbCharacter.asDomainCharacter() : Character = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    hair = hair,
    origin = origin,
    abilities = abilities.split(";"),
    img_url = img_url
)

/**
 * Converts a domain character to a database character.
 * @return A database entity of character.
 */
fun Character.asDbCharacter() : DbCharacter = DbCharacter(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    hair = hair,
    origin = origin,
    abilities = abilities.joinToString (separator = "; "),
    img_url = img_url
)

/**
 * Converts a list of database characters to domain characters.
 * @return A list of domain model characters.
 */
fun List<DbCharacter>.asDomainCharacters() = map { it.asDomainCharacter() }