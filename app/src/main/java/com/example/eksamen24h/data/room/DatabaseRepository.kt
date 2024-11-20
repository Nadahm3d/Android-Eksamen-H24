package com.example.eksamen24h.data.room

import android.content.Context
import androidx.room.Room
import com.example.eksamen24h.data.data_classes.Character
import java.sql.SQLException

object DatabaseRepository {

    private lateinit var _characterDatabase: CharacterDatabase
    private val _characterDao: CharacterDao by lazy { _characterDatabase.characterDao() }

    fun initializeDatabase(context: Context) {
        _characterDatabase = Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            "Character-database"
        ).build()
    }

    suspend fun getDatabaseCharacters(): List<Character> {
        return try {
            _characterDao.getCharacters()
        } catch (e: SQLException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun insertCharacter(character: Character): Long {
        return try {
            _characterDao.insertCharacter(character)
        } catch (e: SQLException) {
            -1L
        }
    }

    suspend fun deleteAllCharacters() {
        try {
            _characterDao.deleteAllCharacters()
        } catch (e: SQLException) {
        } catch (e: Exception) {
        }
    }

    suspend fun deleteCharacter(character: Character): Int {
        return try {
            _characterDao.deleteCharacter(character)
        } catch (e: SQLException) {
            0
        }
    }
}

