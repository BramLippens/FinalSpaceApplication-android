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

interface AppRepository {
    fun getCharacters(): Flow<List<Character>>
    fun getLocations(): Flow<List<Location>>

    suspend fun getCharacterDetail(id: Int): Character?

    suspend fun insertCharacter(character: Character)
    suspend fun insertLocation(location: Location)

    suspend fun refreshCharacters()
    suspend fun refreshLocations()
}

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