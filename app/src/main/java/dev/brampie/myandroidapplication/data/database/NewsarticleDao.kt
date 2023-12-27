package dev.brampie.myandroidapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsarticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsarticle(newsArticles: DbNewsarticle)

    @Query("SELECT * FROM DbNewsarticle")
    fun getAllNewsarticles(): Flow<List<DbNewsarticle>>
}