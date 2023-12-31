package dev.brampie.myandroidapplication.data.database.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: DbLocation)

    @Query("SELECT * FROM locations")
    fun getAllLocations(): Flow<List<DbLocation>>
}