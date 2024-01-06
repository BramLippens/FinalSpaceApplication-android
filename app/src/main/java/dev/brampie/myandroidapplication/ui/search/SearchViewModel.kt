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
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for character search and managing search results.
 *
 * @property appRepository The [AppRepository] instance to fetch character data from.
 */
@OptIn(FlowPreview::class)
class SearchViewModel (
    private val appRepository: AppRepository
): ViewModel() {
    private var _characterName = MutableStateFlow("")

    private val _characterByName = MutableStateFlow<List<Character>>(emptyList())
    /**
     * Represents the list of characters matching the search criteria.
     */
    val characterByName: StateFlow<List<Character>> = _characterByName.asStateFlow()

    /**
     * Fetch characters by name using the provided search query.
     *
     * @param name The name of the character to search for.
     */
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
    init {
        viewModelScope.launch {
            _characterName
                .debounce(1000)
                .collect {name ->
                    if(name.isNotEmpty()) {
                        fetchCharacterByName(name)
                    }else{
                        _characterByName.value = emptyList()
                    }

            }
        }
    }

    fun onQueryChanged(it: String) {
        _characterName.value = it
    }

    companion object {
        private var Instance: SearchViewModel? = null
        /**
         * Factory for creating instances of [SearchViewModel].
         */
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