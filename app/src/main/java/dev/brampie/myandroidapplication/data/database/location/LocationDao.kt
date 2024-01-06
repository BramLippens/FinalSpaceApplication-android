package dev.brampie.myandroidapplication.data.database.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * DAO interface for location-related database operations.
 */
@Dao
interface LocationDao {
    /**
     * Inserts a location into the database, replacing on conflict.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: DbLocation)

    /**
     * Retrieves all locations from the database.
     * @return A flow emitting a list of all locations.
     */
    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<DbLocation>>
}