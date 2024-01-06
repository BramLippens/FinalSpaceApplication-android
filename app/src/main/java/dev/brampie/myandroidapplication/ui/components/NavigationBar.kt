package dev.brampie.myandroidapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.brampie.myandroidapplication.R

/**
 * A custom bottom navigation bar for the Final Space application.
 *
 * @param onHome The click event handler for the "Characters" navigation item.
 * @param onLocation The click event handler for the "Locations" navigation item.
 * @param onSearch The click event handler for the "Search" navigation item.
 * @param currentBackStackEntry The name of the current back stack entry to determine the selected item.
 */
@Composable
fun NavigationBar(
    onHome: () -> Unit,
    onLocation: () -> Unit,
    onSearch: () -> Unit,
    currentBackStackEntry: String?,
) {
    BottomAppBar {
        NavigationBarItem(
            selected = currentBackStackEntry == Destinations.Characters.name,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.go_to_characters)
                )
            })
        NavigationBarItem(
            selected = currentBackStackEntry == Destinations.Locations.name,
            onClick = onLocation,
            icon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = stringResource(R.string.go_to_locations)
                )
            })
        NavigationBarItem(
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
