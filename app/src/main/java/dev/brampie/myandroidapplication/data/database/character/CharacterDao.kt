package dev.brampie.myandroidapplication.data.database.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    /**
     * Inserts a character into the database. Replaces on conflict.
     * @param character The character to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: DbCharacter)

    /**
     * Retrieves all characters from the database.
     * @return A flow emitting a list of all characters.
     */
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<DbCharacter>>

    /**
     * Retrieves a character by its ID.
     * @param id The ID of the character.
     * @return A possibly null single character or null if not found.
     */
    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): DbCharacter?

    /**
     * Retrieves characters matching the given name.
     * @param name The name to match.
     * @return A flow emitting a list of characters with matching names.
     */
    @Query("SELECT * FROM characters WHERE name LIKE '%' || :name || '%'")
    fun getCharactersByName(name: String): Flow<List<DbCharacter>>
}