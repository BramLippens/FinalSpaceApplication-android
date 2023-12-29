package dev.brampie.myandroidapplication.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import dev.brampie.myandroidapplication.R

enum class NewsOverviewScreen(
    @StringRes val title: Int, val icon: ImageVector
){
    Start(title = R.string.go_to_home, icon = Icons.Filled.Home),
    Search(title = R.string.go_to_search, icon = Icons.Filled.Search),

}