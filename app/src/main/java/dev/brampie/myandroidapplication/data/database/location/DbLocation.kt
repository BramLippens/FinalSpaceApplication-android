package dev.brampie.myandroidapplication.data.database.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.location.Location

/**
 * Entity representation of a location for the room database.
 * @property id Unique identifier for the location.
 * @property name Name of the location.
 * @property type Type or category of the location.
 * @property img_url URL to the image representing the location.
 */
@Entity(tableName = "locations")
data class DbLocation(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val type: String,
    val img_url: String
)

/**
 * Converts a database location entity to a domain model location.
 * @return A domain model location with properties mapped from the database entity.
 */
fun DbLocation.asDomainLocation() : Location = Location(
    id = id,
    name = name,
    type = type,
    img_url = img_url
)

/**
 * Converts a domain model location to a database entity.
 * @return A database entity location with properties mapped from the domain model.
 */
fun Location.asDbLocation() : DbLocation = DbLocation(
    id = id,
    name = name,
    type = type,
    img_url = img_url
)

/**
 * Converts a list of database location entities to domain model locations.
 * @return A list of domain model locations.
 */
fun List<DbLocation>.asDomainLocations() = map { it.asDomainLocation() }