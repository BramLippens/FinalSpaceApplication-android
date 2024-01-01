package dev.brampie.myandroidapplication.network.character

import dev.brampie.myandroidapplication.model.character.CharacterDetail

sealed interface ApiCharacterDetailState {
    object Error: ApiCharacterDetailState
    object Loading: ApiCharacterDetailState
    data class Success(val character: CharacterDetail?): ApiCharacterDetailState
}