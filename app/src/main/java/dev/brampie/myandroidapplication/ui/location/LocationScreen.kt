package dev.brampie.myandroidapplication.ui.location

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.network.location.ApiLocationState

/**
 * A Composable function that displays a screen containing a list of locations.
 *
 * @param modifier The modifier to apply to the Composable.
 * @param locationViewModel The ViewModel responsible for location data.
 */
@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    locationViewModel: LocationViewModel = viewModel(factory = LocationViewModel.Factory)
) {
    val locationApiState = locationViewModel.locationApiState
    val uiLocationListState by locationViewModel.uiLocationListState.collectAsState()

    when(locationApiState) {
        is ApiLocationState.Error -> {
            Text(text = stringResource(R.string.error_loading_locations_from_api))
        }

        is ApiLocationState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }

        is ApiLocationState.Success -> {
            LocationList(uiLocationListState.locationList, isLandscape)
        }
    }
}