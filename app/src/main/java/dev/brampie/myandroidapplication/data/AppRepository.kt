package dev.brampie.myandroidapplication.data

import android.content.Context
import android.util.Log
import dev.brampie.myandroidapplication.data.database.character.CharacterDao
import dev.brampie.myandroidapplication.data.database.character.asDbCharacter
import dev.brampie.myandroidapplication.data.database.character.asDomainCharacter
import dev.brampie.myandroidapplication.data.database.character.asDomainCharacters
import dev.brampie.myandroidapplication.data.database.location.LocationDao
import dev.brampie.myandroidapplication.data.database.location.asDbLocation
import dev.brampie.myandroidapplication.data.database.location.asDomainLocations
import dev.brampie.myandroidapplication.model.character.Character
import dev.brampie.myandroidapplication.model.location.Location
import dev.brampie.myandroidapplication.network.ApiService
import dev.brampie.myandroidapplication.network.character.asDomainObject
import dev.brampie.myandroidapplication.network.character.asDomainObjects
import dev.brampie.myandroidapplication.network.getCharactersAsFlow
import dev.brampie.myandroidapplication.network.getLocationsAsFlow
import dev.brampie.myandroidapplication.network.location.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException

/**
 * The `AppRepository` interface defines methods for interacting with the application's data sources.
 */
interface AppRepository {
    /**
     * Get a [Flow] of characters from the data source.
     *
     * @return A [Flow] emitting a list of characters.
     */
    fun getCharacters(): Flow<List<Character>>

    /**
     * Get a [Flow] of locations from the data source.
     *
     * @return A [Flow] emitting a list of locations.
     */
    fun getLocations(): Flow<List<Location>>

    /**
     * Get a character by its unique identifier.
     *
     * @param id The unique identifier of the character.
     * @return The character with the given [id], or null if no character with that id exists.
     */
    suspend fun getCharacterDetail(id: Int): Character?

    /**
     * Get a [Flow] of characters with a name matching the given [name].
     *
     * @param name The name to match.
     * @return A [Flow] emitting a list of characters with a matching name.
     */
    fun getCharacterByName(name: String): Flow<List<Character>>

    /**
     * Insert a character into the data source.
     *
     * @param character The character to insert.
     */
    suspend fun insertCharacter(character: Character)

    /**
     * Insert a location into the data source.
     *
     * @param location The location to insert.
     */
    suspend fun insertLocation(location: Location)

    /**
     * Refresh the characters stored in the data source.
     */
    suspend fun refreshCharacters()

    /**
     * Refresh the locations stored in the data source.
     */
    suspend fun refreshLocations()
}

/**
 * The `AppRepository` implementation for the application.
 *
 * @property apiService The [ApiService] for making network requests.
 * @property characterDao The [CharacterDao] for accessing and managing [DbCharacter] entities in the database.
 * @property locationDao The [LocationDao] for accessing and managing [DbLocation] entities in the database.
 * @property context The application context.
 */
class CachingAppRepository(
    private val apiService: ApiService,
    private val characterDao: CharacterDao,
    private val locationDao: LocationDao,
    context: Context
) : AppRepository {
    override fun getCharacters(): Flow<List<Character>> {
        return characterDao.getAllCharacters().map {
            it.asDomainCharacters()
        }
    }

    override fun getLocations(): Flow<List<Location>> {
        return locationDao.getAllLocations().map {
            it.asDomainLocations()
        }
    }

    override suspend fun getCharacterDetail(id: Int): Character {
        val local = characterDao.getCharacterById(id)
        if(local != null) {
            return local.asDomainCharacter()
        }else{
            val remote = apiService.getCharacterDetailById(id)
            insertCharacter(remote.asDomainObject())
            return remote.asDomainObject()
        }
    }

    override fun getCharacterByName(name: String): Flow<List<Character>> {
        return characterDao.getCharactersByName(name).map {
            it.asDomainCharacters()
        }
    }

    override suspend fun insertCharacter(character: Character) {
        characterDao.insertCharacter(character.asDbCharacter())
    }

    override suspend fun insertLocation(location: Location) {
        locationDao.insertLocation(location.asDbLocation())
    }

    override suspend fun refreshCharacters() {
        try {
            Log.i("AppRepository", "refreshing characters: ")
            apiService.getCharactersAsFlow().asDomainObjects().collect {
                    value ->
                for (character in value) {
                    insertCharacter(character)
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
            Log.i("AppRepository", "Error while refreshing characters: $e")
            e.printStackTrace()
        }
    }

    override suspend fun refreshLocations() {
        try {
            Log.i("AppRepository", "refreshing locations: ")
            apiService.getLocationsAsFlow().asDomainObjects().collect {
                    value ->
                for (location in value) {
                    insertLocation(location)
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
            Log.i("AppRepository", "Error while refreshing locations: $e")
            e.printStackTrace()
        }
    }
}