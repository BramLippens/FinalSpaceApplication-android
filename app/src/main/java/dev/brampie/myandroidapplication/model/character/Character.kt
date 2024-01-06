package dev.brampie.myandroidapplication.model.character

/**
 * Represents a character in the application.
 *
 * @property id The unique identifier of the character.
 * @property name The name of the character.
 * @property status The status of the character (e.g., "Alive," "Dead," or "Unknown").
 * @property species The species of the character.
 * @property gender The gender of the character.
 * @property hair The hair description of the character.
 * @property origin The origin of the character.
 * @property abilities A list of abilities or traits possessed by the character.
 * @property img_url The URL to the character's image.
 */
class Character (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val hair: String,
    val origin: String,
    val abilities: List<String>,
    val img_url: String
)