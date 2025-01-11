package com.arturmaslov.lkz_droid.data.source

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.arturmaslov.lkz_droid.data.model.WordEntity

@Database(
    entities = [
        WordEntity::class,
    ], version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    // The associated DAOs for the database
    abstract val wordDao: WordDao?

    companion object {
        const val DATABASE_NAME = "LKZ_DB"
    }
}

@Dao
interface WordDao {
    @Query("SELECT * FROM wordentity")
    fun getAllWords(): List<WordEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity): Long
}