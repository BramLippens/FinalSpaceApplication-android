package dev.brampie.myandroidapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.ui.components.Destinations
import dev.brampie.myandroidapplication.ui.components.NavigationBar
import dev.brampie.myandroidapplication.ui.components.NewsAppBar
import dev.brampie.myandroidapplication.ui.navigation.NewsOverviewScreen
import dev.brampie.myandroidapplication.ui.newsarticles.NewsarticleDetailScreen
import dev.brampie.myandroidapplication.ui.overviewScreen.NewsOverview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(
    navigationType: NewsNavigationType,
    navController: NavHostController = rememberNavController()
){
    val currentScreenTitle = stringResource(R.string.go_to_home)

    val goHome: () -> Unit = {
        navController.popBackStack(
            Destinations.Start.name,
            inclusive = false,
        )
    }
    val goSearch: () -> Unit = {
        navController.navigate(NewsOverviewScreen.Search.name)
    }
    val goNewsarticleDetail: (Int) -> Unit = { id ->
        navController.navigate("${Destinations.NewsDetail.name}/$id")
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
                //TODO: navController cant be passed
                NavHost(
                    navController = navController,
                    startDestination = NewsOverviewScreen.Start.name,
                    Modifier.padding(innerPadding)
                ) {
                    composable(NewsOverviewScreen.Start.name) {
                        NewsOverview(
                            onNewsarticleClick = goNewsarticleDetail
                        )
                    }
                    composable("${Destinations.NewsDetail.name}/{id}") {
                        NewsarticleDetailScreen(it.arguments?.getString("id")?.toInt() ?: 0)
                    }
                }
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