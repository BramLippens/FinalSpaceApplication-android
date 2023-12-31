package dev.brampie.myandroidapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.ui.character.CharacterScreen
import dev.brampie.myandroidapplication.ui.components.Destinations
import dev.brampie.myandroidapplication.ui.components.NavigationBar
import dev.brampie.myandroidapplication.ui.components.NewsAppBar
import dev.brampie.myandroidapplication.ui.location.LocationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalSpaceApp(
    navigationType: NavigationType,
    navController: NavHostController = rememberNavController()
){
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    val currentScreenTitle = stringResource(R.string.go_to_home)

    val goHome: () -> Unit = {
        navController.popBackStack(
            Destinations.Characters.name,
            inclusive = false,
        )
    }
    val goLocation: () -> Unit = {
        navController.navigate(Destinations.Locations.name)
    }
    val goCharacterDetail: (Int) -> Unit = { id ->
        navController.navigate("${Destinations.CharacterDetail.name}/${id}")
    }

    when(navigationType){
        NavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                topBar = {
                    NewsAppBar(currentScreentitle = currentScreenTitle)
                },
                bottomBar = {
                    NavigationBar(onHome = goHome, onLocation = goLocation, currentBackStackEntry?.destination?.route)
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Destinations.Characters.name,
                    Modifier.padding(innerPadding)
                ) {
                    composable(Destinations.Characters.name) {
                        CharacterScreen(
                            onClick = goCharacterDetail,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                    composable(Destinations.Locations.name) {
                        LocationScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
        NavigationType.NAVIGATION_RAIL -> {
            Text(text = "Navigation Rail")
        }
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            Text(text = "Permanent Navigation Drawer")
        }
    }
}