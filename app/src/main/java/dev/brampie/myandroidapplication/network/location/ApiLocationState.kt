package dev.brampie.myandroidapplication.network.location

import dev.brampie.myandroidapplication.model.location.Location

/**
 * Represents the state of a location retrieved from the API.
 */
sealed interface ApiLocationState {
    object Error: ApiLocationState
    object Loading: ApiLocationState
    object Success: ApiLocationState
}

data class LocationListState(val locationList: List<Location> = listOf())