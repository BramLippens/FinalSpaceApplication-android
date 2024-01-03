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
import dev.brampie.myandroidapplication.ui.character.detail.CharacterDetailScreen
import dev.brampie.myandroidapplication.ui.character.overview.CharacterScreen
import dev.brampie.myandroidapplication.ui.components.Destinations
import dev.brampie.myandroidapplication.ui.components.FinalSpaceAppBar
import dev.brampie.myandroidapplication.ui.components.NavigationBar
import dev.brampie.myandroidapplication.ui.components.NavigationType
import dev.brampie.myandroidapplication.ui.location.LocationScreen
import dev.brampie.myandroidapplication.ui.search.SearchScreen
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
    val goSearch: () -> Unit = {
        navController.navigate(Destinations.Search.name)
    }

    when(navigationType){
        NavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                topBar = {
                    FinalSpaceAppBar(currentScreentitle = currentScreenTitle)
                },
                bottomBar = {
                    NavigationBar(
                        onHome = goHome,
                        onLocation = goLocation,
                        onSearch = goSearch,
                        currentBackStackEntry?.destination?.route
                    )
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
                    composable("${Destinations.CharacterDetail.name}/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                        if (id == null) {
                            Text(text = "Error")
                        } else {
                            CharacterDetailScreen(
                                characterId = id,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                    composable(Destinations.Search.name) {
                        SearchScreen(onClick =goCharacterDetail,modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
        else-> {

        }
    }
}