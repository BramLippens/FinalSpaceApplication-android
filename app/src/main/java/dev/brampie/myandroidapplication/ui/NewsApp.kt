package dev.brampie.myandroidapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.ui.components.NavigationBar
import dev.brampie.myandroidapplication.ui.components.NewsAppBar
import dev.brampie.myandroidapplication.ui.navigation.NewsOverviewScreen
import dev.brampie.myandroidapplication.ui.navigation.navComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(
    navigationType: NewsNavigationType,
    navController: NavHostController = rememberNavController()
){
    val currentScreenTitle = stringResource(R.string.go_to_home)

    val goHome: () -> Unit = {
        navController.popBackStack(
            NewsOverviewScreen.Start.name,
            inclusive = false,
        )
    }
    val goSearch: () -> Unit = {
        navController.navigate(NewsOverviewScreen.Search.name)
    }

    when(navigationType){
        NewsNavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                topBar = {
                    NewsAppBar(currentScreentitle = currentScreenTitle)
                },
                bottomBar = {
                    NavigationBar(onHome = goHome, onNews = goSearch)
                }
            ) { innerPadding ->
                navComponent(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
        NewsNavigationType.NAVIGATION_RAIL -> {
            Text(text = "Navigation Rail")
        }
        NewsNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            Text(text = "Permanent Navigation Drawer")
        }
    }
}