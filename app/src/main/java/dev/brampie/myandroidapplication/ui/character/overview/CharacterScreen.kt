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

@Composable
fun CharacterScreen(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    characterViewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory)
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
            CharacterList(uiCharacterListState.characterList, onClick)
        }
    }
}