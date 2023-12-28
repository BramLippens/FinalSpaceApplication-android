package dev.brampie.myandroidapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.brampie.myandroidapplication.ui.overviewScreen.NewsOverview

@Composable
fun navComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    //TODO: navController cant be passed
    NavHost(
        navController = navController,
        startDestination = NewsOverviewScreen.Start.name,
        modifier = modifier
    ) {
        composable(NewsOverviewScreen.Start.name) {
            NewsOverview()
        }
        composable(NewsOverviewScreen.Search.name) {
            NewsOverview()
        }
    }
}