package dev.brampie.myandroidapplication.network.character

import dev.brampie.myandroidapplication.model.character.Character

sealed interface ApiCharacterState {
    object Error: ApiCharacterState
    object Loading: ApiCharacterState
    object Success: ApiCharacterState
}

data class CharacterListState(val characterList: List<Character> = listOf())