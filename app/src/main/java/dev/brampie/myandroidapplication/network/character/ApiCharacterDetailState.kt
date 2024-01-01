package dev.brampie.myandroidapplication.network.character

import dev.brampie.myandroidapplication.model.character.Character

sealed interface ApiCharacterDetailState {
    object Error: ApiCharacterDetailState
    object Loading: ApiCharacterDetailState
    data class Success(val character: Character?): ApiCharacterDetailState
}