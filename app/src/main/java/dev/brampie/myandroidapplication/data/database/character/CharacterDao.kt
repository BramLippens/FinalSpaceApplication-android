package dev.brampie.myandroidapplication.data.database.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DbCharacter)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<DbCharacter>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): DbCharacter?

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :name || '%'")
    fun getCharactersByName(name: String): Flow<List<DbCharacter>>
}