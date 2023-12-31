package dev.brampie.myandroidapplication.ui.character.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.brampie.myandroidapplication.FinalSpaceApplication
import dev.brampie.myandroidapplication.data.AppRepository
import dev.brampie.myandroidapplication.network.character.ApiCharacterDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for character details.
 *
 * @property appRepository The [AppRepository] instance to fetch character details from.
 */
class CharacterDetailViewModel (
    private val appRepository: AppRepository
) : ViewModel(
){
    private val _characterState = MutableStateFlow<ApiCharacterDetailState>(ApiCharacterDetailState.Loading)
    val characterState: StateFlow<ApiCharacterDetailState> = _characterState.asStateFlow()

    /**
     * Fetch detailed information about a character by its [id].
     *
     * @param id The unique identifier of the character to retrieve.
     */
    fun getCharacterDetail(id: Int) {
        viewModelScope.launch {
            try{
                val result = appRepository.getCharacterDetail(id)
                _characterState.value = ApiCharacterDetailState.Success(result)
                Log.i("CharacterDetailViewModel", "getCharacterDetail: success")
            }catch (e: Exception){
                _characterState.value = ApiCharacterDetailState.Error
                Log.e("CharacterDetailViewModel", "getCharacterDetail: ${e.message}")
            }
        }
    }

    companion object{
        /**
         * Factory for creating instances of [CharacterDetailViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FinalSpaceApplication)
                val appRepository = application.container.appRepository
                CharacterDetailViewModel(appRepository)
            }
        }
    }
}