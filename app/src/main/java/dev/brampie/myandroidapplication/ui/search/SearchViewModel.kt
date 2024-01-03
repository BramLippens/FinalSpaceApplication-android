package dev.brampie.myandroidapplication.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.brampie.myandroidapplication.FinalSpaceApplication
import dev.brampie.myandroidapplication.data.AppRepository
import dev.brampie.myandroidapplication.model.character.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SearchViewModel (
    private val appRepository: AppRepository
): ViewModel() {
    private val _characterByName = MutableStateFlow<List<Character>>(emptyList())
    val characterByName: StateFlow<List<Character>> = _characterByName.asStateFlow()

    fun fetchCharacterByName(name: String) {
        viewModelScope.launch {
            try {
                _characterByName.value = appRepository.getCharacterByName(name).first()
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Error fetching character: ${e.message}")
                _characterByName.value = emptyList()
            }
        }
    }

    companion object {
        private var Instance: SearchViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application =
                        (this[APPLICATION_KEY] as FinalSpaceApplication)
                    val appRepository = application.container.appRepository
                    Instance = SearchViewModel(appRepository)
                }
                Instance!!
            }
        }
    }
}