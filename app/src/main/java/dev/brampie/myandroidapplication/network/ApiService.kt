package dev.brampie.myandroidapplication.network

import android.util.Log
import dev.brampie.myandroidapplication.network.character.ApiCharacter
import dev.brampie.myandroidapplication.network.location.ApiLocation
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface ApiService{
    @GET("character")
    suspend fun getCharacters(): List<ApiCharacter>

    @GET("location")
    suspend fun getLocations(): List<ApiLocation>
}

fun ApiService.getCharactersAsFlow() = flow {
    try {
        emit(getCharacters())
    } catch (e: Exception) {
        Log.e("ApiService", "getCharactersAsFlow: ", e)
    }
}

fun ApiService.getLocationsAsFlow() = flow {
    try {
        emit(getLocations())
    } catch (e: Exception) {
        Log.e("ApiService", "getLocationsAsFlow: ", e)
    }
}