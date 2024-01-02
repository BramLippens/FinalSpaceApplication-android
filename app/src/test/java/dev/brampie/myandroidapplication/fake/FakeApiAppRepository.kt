package dev.brampie.myandroidapplication.fake

import dev.brampie.myandroidapplication.data.AppRepository
import dev.brampie.myandroidapplication.model.character.Character
import dev.brampie.myandroidapplication.model.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeApiAppRepository : AppRepository {
    override fun getCharacters(): Flow<List<Character>> {
        return flowOf(FakeDataSource.characters.toMutableList())
    }

    override fun getLocations(): Flow<List<Location>> {
        return flowOf(FakeDataSource.locations.toMutableList())
    }

    override suspend fun getCharacterDetail(id: Int): Character? {
        return FakeDataSource.characters.find { it.externalId == id }
    }

    override suspend fun insertCharacter(character: Character) {
        FakeDataSource.characters.toMutableList().add(character)
    }

    override suspend fun insertLocation(location: Location) {
        FakeDataSource.locations.toMutableList().add(location)
    }

    override suspend fun refreshCharacters() {
        FakeDataSource.characters.toMutableList().clear()
    }

    override suspend fun refreshLocations() {
        FakeDataSource.locations.toMutableList().clear()
    }

}