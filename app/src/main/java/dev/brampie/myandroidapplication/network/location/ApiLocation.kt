package dev.brampie.myandroidapplication.network.location

import dev.brampie.myandroidapplication.model.location.Location
import kotlinx.serialization.Serializable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Serializable
data class ApiLocation(
    val id: Int,
    val name: String,
    val type: String,
    val img_url: String
)

@Serializable
data class ApiLocationResponse(
    val results: List<ApiLocation>
)

fun ApiLocationResponse.asDomainObjects(): List<Location> {
    return results.map {
        Location(
            externalId = it.id,
            name = it.name,
            type = it.type,
            img_url = it.img_url
        )
    }
}

fun Flow<ApiLocationResponse>.asDomainObjects(): Flow<List<Location>> {
    return map { it.asDomainObjects() }
}