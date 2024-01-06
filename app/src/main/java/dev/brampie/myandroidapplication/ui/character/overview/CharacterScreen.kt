package dev.brampie.myandroidapplication.ui.character.overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.network.character.ApiCharacterState

/**
 * A Composable function that displays a screen containing a list of characters.
 *
 * @param onClick The click event handler for character items.
 * @param modifier The modifier to apply to the Composable.
 * @param characterViewModel The ViewModel responsible for character data.
 */
@Composable
fun CharacterScreen(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    characterViewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory),
    isLandscape: Boolean
) {
    val characterApiState = characterViewModel.characterApiState
    val uiCharacterListState by characterViewModel.uiCharacterListState.collectAsState()

    when(characterApiState) {
        is ApiCharacterState.Error -> {
            Text(text = stringResource(R.string.error_loading_characters_from_api))
        }

        is ApiCharacterState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }

        is ApiCharacterState.Success -> {
            CharacterList(uiCharacterListState.characterList, onClick, isLandscape)
        }
    }
}