package com.example.eksamen24h.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eksamen24h.data.data_classes.Character

@Database(
    entities = [Character::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

