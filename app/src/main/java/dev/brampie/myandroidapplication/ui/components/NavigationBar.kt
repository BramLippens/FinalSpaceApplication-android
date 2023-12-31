package dev.brampie.myandroidapplication.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Home
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
) {
    val context = LocalContext.current

    BottomAppBar {
        NavigationBarItem(
            selected = true,
            onClick = onHome,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(R.string.go_to_home)
                )
            })
        NavigationBarItem(
            selected = false,
            onClick = onLocation,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Article,
                    contentDescription = stringResource(R.string.go_to_characters)
                )
            })
    }
}
