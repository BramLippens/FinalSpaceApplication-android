package dev.brampie.myandroidapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.brampie.myandroidapplication.data.database.character.CharacterDao
import dev.brampie.myandroidapplication.data.database.character.DbCharacter
import dev.brampie.myandroidapplication.data.database.location.DbLocation
import dev.brampie.myandroidapplication.data.database.location.LocationDao


@Database(
    entities = [DbCharacter::class, DbLocation::class],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

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