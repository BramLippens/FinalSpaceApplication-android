package dev.brampie.myandroidapplication.ui.character.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val characterByName by characterViewModel.characterByName.collectAsState()

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