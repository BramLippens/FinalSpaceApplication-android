package dev.brampie.myandroidapplication.network.character

import dev.brampie.myandroidapplication.model.character.Character

/**
 * Represents the state of a character retrieved from the API.
 */
sealed interface ApiCharacterDetailState {
    object Error: ApiCharacterDetailState
    object Loading: ApiCharacterDetailState
    data class Success(val character: Character?): ApiCharacterDetailState
}