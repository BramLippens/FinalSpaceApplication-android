package dev.brampie.myandroidapplication.ui.character.overview

import dev.brampie.myandroidapplication.model.character.Character

/**
 * Represents the current state of character data.
 *
 * @property currentCharacterList The list of [Character] objects in the current state.
 */
data class CharacterState (
    val currentCharacterList: List<Character>
)