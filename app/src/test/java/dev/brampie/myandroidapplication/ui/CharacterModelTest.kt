package dev.brampie.myandroidapplication.ui

import dev.brampie.myandroidapplication.fake.FakeApiAppRepository
import dev.brampie.myandroidapplication.network.character.ApiCharacterState
import dev.brampie.myandroidapplication.ui.character.overview.CharacterViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterModelTest {
    private lateinit var viewModel: CharacterViewModel

    @get:Rule
    val testDispatcherRule = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = CharacterViewModel(FakeApiAppRepository())
    }

    @Test
    fun getCharacters_methodCall_StateIsSuccessAfterCall() {
        // Arrange
        val expectedState = ApiCharacterState.Success

        // Act
        viewModel.getApiCharacters()

        // Assert
        Assert.assertEquals(expectedState, viewModel.characterApiState)
    }
}