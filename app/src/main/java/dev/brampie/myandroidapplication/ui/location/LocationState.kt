package dev.brampie.myandroidapplication.ui.location

import dev.brampie.myandroidapplication.model.location.Location

/**
 * Represents the current state of location data.
 *
 * @property currentLocationList The list of [Location] objects in the current state.
 */
data class LocationState (
    val currentLocationList: List<Location>
)