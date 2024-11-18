package com.example.eksamen24h.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eksamen24h.data.data_classes.Character

// @Dao markerer denne interfacen som et Data Access Object (DAO)
@Dao
interface CharacterDao {
    // Denne funksjonen leser alle karakterer fra databasen
    @Query("SELECT * FROM Character")
    suspend fun getCharacters(): List<Character>

    // Denne funksjonen legger til en ny karakter i databasen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character): Long

    // Denne funksjonen sletter alle karakterer fra databasen
    @Query("DELETE FROM Character")
    suspend fun deleteAllCharacters()

    // Denen funskjonen sletter en singel karakter
    @Delete
    suspend fun deleteCharacter(character: Character): Int
}