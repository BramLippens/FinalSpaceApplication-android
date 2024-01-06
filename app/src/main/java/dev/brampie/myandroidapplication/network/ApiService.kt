package dev.brampie.myandroidapplication.network

import android.util.Log
import dev.brampie.myandroidapplication.network.character.ApiCharacter
import dev.brampie.myandroidapplication.network.location.ApiLocation
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * The `ApiService` interface defines methods for interacting with the API.
 */
interface ApiService{
    /**
     * Retrieve a list of characters from the API.
     *
     * @return A list of [ApiCharacter] objects representing characters.
     */
    @GET("character")
    suspend fun getCharacters(): List<ApiCharacter>

    /**
     * Retrieve a list of locations from the API.
     *
     * @return A list of [ApiLocation] objects representing locations.
     */
    @GET("location")
    suspend fun getLocations(): List<ApiLocation>

    /**
     * Retrieve detailed information about a character by its [id] from the API.
     *
     * @param id The unique identifier of the character.
     * @return An [ApiCharacter] object representing the character's details.
     */
    @GET("character/{id}")
    suspend fun getCharacterDetailById(@Path("id") id: Int): ApiCharacter
}

/**
 * Extension function to retrieve characters as a [Flow].
 *
 * @receiver The [ApiService] instance.
 * @return A [Flow] emitting a list of [ApiCharacter] objects representing characters.
 */
fun ApiService.getCharactersAsFlow() = flow {
    try {
        emit(getCharacters())
    } catch (e: Exception) {
        Log.e("ApiService", "getCharactersAsFlow: ", e)
    }
}

/**
 * Extension function to retrieve locations as a [Flow].
 *
 * @receiver The [ApiService] instance.
 * @return A [Flow] emitting a list of [ApiLocation] objects representing locations.
 */
fun ApiService.getLocationsAsFlow() = flow {
    try {
        emit(getLocations())
    } catch (e: Exception) {
        Log.e("ApiService", "getLocationsAsFlow: ", e)
    }
}