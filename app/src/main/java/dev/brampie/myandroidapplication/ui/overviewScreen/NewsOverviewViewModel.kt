package dev.brampie.myandroidapplication.ui.overviewScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.brampie.myandroidapplication.NewsApplication
import dev.brampie.myandroidapplication.data.AppRepository
import dev.brampie.myandroidapplication.network.NewsApiState
import dev.brampie.myandroidapplication.network.NewsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsOverviewViewModel(private val appRepository: AppRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(
        OverviewState(
            listOf()
        )
    )

    //val uiState: StateFlow<OverviewState> = _uiState.asStateFlow()

    lateinit var uiNewsListState: StateFlow<NewsListState>

    var newsApiState: NewsApiState by mutableStateOf(NewsApiState.Loading)
        private set

    init {
        getApiNews()
    }

    fun getApiNews(){
        try{
            viewModelScope.launch {appRepository.refresh()}
            uiNewsListState = appRepository.getNewsArticles().map { NewsListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
                    initialValue = NewsListState(listOf())
                )
            newsApiState = NewsApiState.Success
        } catch (e: Exception){
            newsApiState = NewsApiState.Error
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NewsApplication)
                val appRepository = application.container.appRepository
                NewsOverviewViewModel(appRepository)
            }
        }
    }
}