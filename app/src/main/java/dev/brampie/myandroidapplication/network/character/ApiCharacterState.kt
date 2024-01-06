package dev.brampie.myandroidapplication.network.character

import dev.brampie.myandroidapplication.model.character.Character

/**
 * Represents the state of a character retrieved from the API.
 */
sealed interface ApiCharacterState {
    object Error: ApiCharacterState
    object Loading: ApiCharacterState
    object Success: ApiCharacterState
}

data class CharacterListState(val characterList: List<Character> = listOf())