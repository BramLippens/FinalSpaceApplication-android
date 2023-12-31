package dev.brampie.myandroidapplication.ui.location

import android.text.Spannable.Factory
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
import dev.brampie.myandroidapplication.network.location.ApiLocationState
import dev.brampie.myandroidapplication.network.location.LocationListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocationViewModel (
    private val appRepository: AppRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(
        LocationState(
            listOf()
        )
    )

    val uiState: StateFlow<LocationState> = _uiState.asStateFlow()
    lateinit var uiLocationListState: StateFlow<LocationListState>

    var locationApiState: ApiLocationState by mutableStateOf(ApiLocationState.Loading)
        private set

    init {
        getApiLocations()
    }

    private fun getApiLocations() {
        try{
            Log.i("LocationViewModel", "getApiLocations: ")
            viewModelScope.launch { appRepository.refreshLocations() }
            uiLocationListState = appRepository.getLocations().map { LocationListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
                    initialValue = LocationListState(listOf())
                )
            locationApiState = ApiLocationState.Success
        }catch (e: Exception){
            locationApiState = ApiLocationState.Error
        }
    }

    companion object{
        private var Instance: LocationViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if(Instance == null){
                    val application = (this[APPLICATION_KEY] as FinalSpaceApplication)
                    val appRepository = application.container.appRepository
                    Instance = LocationViewModel(appRepository)
                }
                Instance!!
            }
        }
    }
}