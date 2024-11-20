package com.example.eksamen24h.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eksamen24h.data.data_classes.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM Character")
    suspend fun getCharacters(): List<Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character): Long

    @Query("DELETE FROM Character")
    suspend fun deleteAllCharacters()

    @Delete
    suspend fun deleteCharacter(character: Character): Int
}
