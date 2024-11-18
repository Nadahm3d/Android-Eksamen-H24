package com.example.eksamen24h.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eksamen24h.data.data_classes.Character

// Dette er en Room-database for Rick and Morty-applikasjonen.
@Database(
    entities = [Character::class], // Bruker Character som en datamodell i databasen
    version = 1, // Dette er versjon 1 av databasen
    exportSchema = false // Vi eksporterer ikke database-skjemaet
)
abstract class CharacterDatabase : RoomDatabase() {
    // Funksjon for å få tilgang til dataoperasjoner for Character
    abstract fun characterDao(): CharacterDao
}
