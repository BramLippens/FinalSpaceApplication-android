package dev.brampie.myandroidapplication.network.location

import dev.brampie.myandroidapplication.model.location.Location
import kotlinx.serialization.Serializable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Represents a location retrieved from the API.
 *
 * @property id The unique identifier of the location.
 * @property name The name of the location.
 * @property type The type or category of the location.
 * @property img_url The URL to the image representing the location.
 */
@Serializable
data class ApiLocation(
    val id: Int,
    val name: String,
    val type: String,
    val img_url: String
)

/**
 * Converts a list of [ApiLocation] objects to a list of [Location] objects.
 *
 * @receiver The list of [ApiLocation] objects to convert.
 * @return A list of [Location] objects.
 */
fun List<ApiLocation>.asDomainObjects(): List<Location> {
    return map {
        Location(
            id = it.id,
            name = it.name,
            type = it.type,
            img_url = it.img_url
        )
    }
}

/**
 * Converts a list of [ApiLocation] objects to a list of [Location] objects.
 *
 * @receiver The list of [ApiLocation] objects to convert.
 * @return A list of [Location] objects.
 */
fun Flow<List<ApiLocation>>.asDomainObjects(): Flow<List<Location>> {
    return map { it.asDomainObjects() }
}