package dev.brampie.myandroidapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.brampie.myandroidapplication.data.database.character.CharacterDao
import dev.brampie.myandroidapplication.data.database.character.DbCharacter
import dev.brampie.myandroidapplication.data.database.location.DbLocation
import dev.brampie.myandroidapplication.data.database.location.LocationDao

/**
 * This is the main AppDatabase class for your application.
 *
 * @property characterDao The Data Access Object (DAO) for working with [DbCharacter] entities.
 * @property locationDao The Data Access Object (DAO) for working with [DbLocation] entities.
 */
@Database(
    entities = [DbCharacter::class, DbLocation::class],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    /**
     * Get the CharacterDao for accessing and managing [DbCharacter] entities in the database.
     *
     * @return The CharacterDao instance.
     */
    abstract fun characterDao(): CharacterDao

    /**
     * Get the LocationDao for accessing and managing [DbLocation] entities in the database.
     *
     * @return The LocationDao instance.
     */
    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Get an instance of the AppDatabase using the provided [context].
         *
         * If an instance already exists, it will be returned. If not, a new instance will be created.
         *
         * @param context The application context.
         * @return The AppDatabase instance.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration() // Handle migrations by destroying and rebuilding the database
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}