package dev.brampie.myandroidapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import dev.brampie.myandroidapplication.R

@Composable
fun NavigationBar(
    onHome: () -> Unit,
    onLocation: () -> Unit,
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
    }
}
