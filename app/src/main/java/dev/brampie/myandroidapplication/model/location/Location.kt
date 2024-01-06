package dev.brampie.myandroidapplication.model.location

/**
 * Represents a location in the application.
 *
 * @property id The unique identifier of the location.
 * @property name The name of the location.
 * @property type The type or category of the location.
 * @property img_url The URL to the image representing the location.
 */
class Location (
    val id: Int,
    val name: String,
    val type: String,
    val img_url: String
)