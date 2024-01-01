package dev.brampie.myandroidapplication.ui.character.overview

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.brampie.myandroidapplication.FinalSpaceApplication
import dev.brampie.myandroidapplication.data.AppRepository
import dev.brampie.myandroidapplication.network.character.ApiCharacterState
import dev.brampie.myandroidapplication.network.character.CharacterListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterViewModel (
    private val appRepository: AppRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(
        CharacterState(
            listOf()
        )
    )

    val uiState: StateFlow<CharacterState> = _uiState.asStateFlow()

    lateinit var uiCharacterListState: StateFlow<CharacterListState>

    var characterApiState: ApiCharacterState by mutableStateOf(ApiCharacterState.Loading)
        private set

    init {
        getApiCharacters()
    }

    private fun getApiCharacters() {
        try{
            Log.i("CharacterViewModel", "getApiCharacters: ")
            viewModelScope.launch { appRepository.refreshCharacters() }
            uiCharacterListState = appRepository.getCharacters().map { CharacterListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
                    initialValue = CharacterListState(listOf())
                )
            characterApiState = ApiCharacterState.Success
            Log.i("CharacterViewModel", "getApiCharacters: success")
        }catch (e: Exception){
            Log.i("CharacterViewModel", "getApiCharacters: ${e.message}")
            characterApiState = ApiCharacterState.Error
        }
    }

    companion object{
        private var Instance: CharacterViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if(Instance == null){
                    val application = (this[APPLICATION_KEY] as FinalSpaceApplication)
                    val appRepository = application.container.appRepository
                    Instance = CharacterViewModel(appRepository)
                }
                Instance!!
            }
        }
    }
}
