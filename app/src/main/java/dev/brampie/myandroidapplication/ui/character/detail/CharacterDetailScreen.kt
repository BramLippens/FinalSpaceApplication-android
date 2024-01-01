package dev.brampie.myandroidapplication.ui.character.detail

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.model.character.Character
import dev.brampie.myandroidapplication.network.character.ApiCharacterDetailState

@Composable
fun CharacterDetail(
    character: Character
) {
    Text(text = character.name)
}

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    modifier: Modifier = Modifier,
    characterDetailViewModel: CharacterDetailViewModel = viewModel(factory = CharacterDetailViewModel.Factory)
) {
    LaunchedEffect(characterId) {
        characterDetailViewModel.getCharacterDetail(characterId)
    }
//    characterDetailViewModel.getCharacterDetail(characterId)

    when(val characterDetailApiState = characterDetailViewModel.characterState.collectAsState().value) {
        is ApiCharacterDetailState.Error -> {
            Text(text = stringResource(R.string.error))
        }
        is ApiCharacterDetailState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }
        is ApiCharacterDetailState.Success -> {
            Log.i("CharacterDetailScreen", "CharacterDetailScreen: ${characterDetailApiState.character}")
            characterDetailApiState.character?.let { CharacterDetail(character = it) }
        }
    }
}