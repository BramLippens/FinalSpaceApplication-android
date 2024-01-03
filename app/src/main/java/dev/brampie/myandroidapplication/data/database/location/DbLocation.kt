package dev.brampie.myandroidapplication.data.database.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.location.Location

@Entity(tableName = "locations")
data class DbLocation(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val type: String,
    val img_url: String
)

fun DbLocation.asDomainLocation() : Location = Location(
    id = id,
    name = name,
    type = type,
    img_url = img_url
)

fun Location.asDbLocation() : DbLocation = DbLocation(
    id = id,
    name = name,
    type = type,
    img_url = img_url
)

fun List<DbLocation>.asDomainLocations() = map { it.asDomainLocation() }