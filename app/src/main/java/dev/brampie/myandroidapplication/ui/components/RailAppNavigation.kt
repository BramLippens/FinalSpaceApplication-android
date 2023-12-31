package dev.brampie.myandroidapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.brampie.myandroidapplication.R

/**
 * A custom navigation rail layout for the Final Space application.
 *
 * @param onHome The click event handler for the "Characters" navigation item.
 * @param onLocation The click event handler for the "Locations" navigation item.
 * @param onSearch The click event handler for the "Search" navigation item.
 * @param currentBackStackEntry The name of the current back stack entry to determine the selected item.
 */
@Composable
fun RailAppNavigation(
    onHome: () -> Unit,
    onLocation: () -> Unit,
    onSearch: () -> Unit,
    currentBackStackEntry: String?,
) {
    NavigationRail {
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Characters.name,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.go_to_characters)
                )
            })
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Locations.name,
            onClick = onLocation,
            icon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = stringResource(R.string.go_to_locations)
                )
            })
        NavigationRailItem(
            selected = currentBackStackEntry == Destinations.Search.name,
            onClick = onSearch,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.go_to_search)
                )
            })
    }
}