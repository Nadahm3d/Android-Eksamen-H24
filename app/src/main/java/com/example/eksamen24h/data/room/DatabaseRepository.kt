package com.example.eksamen24h.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.eksamen24h.data.data_classes.Character
import java.sql.SQLException

object DatabaseRepository {
    // Jobbe med databsen
    private lateinit var _characterDatabase: CharacterDatabase
    private val _characterDao: CharacterDao by lazy { _characterDatabase.characterDao() }

    // Initialiserer databasen ved oppstart
    fun initializeDatabase(context: Context) {
        _characterDatabase = Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            "Character-database"
        ).build()
    }

    // Henter alle karakterer fra databasen
    suspend fun getDatabaseCharacters(): List<Character> {
        return try {
            _characterDao.getCharacters()
        } catch (e: SQLException) {
            Log.d("Databasefeil", e.toString())
            emptyList()
        } catch (e: Exception) {
            Log.d("Databasefeil", e.toString())
            emptyList()
        }
    }

    // Legger til en ny karakter i databasen
    suspend fun insertCharacter (character: com.example.eksamen24h.data.data_classes.Character) : Long{
        try {
            return _characterDao.insertCharacter(character)
        } catch (e: SQLException){
            return -1L
        }
    }


    // Sletter alle karakterer i databasen
    suspend fun deleteAllCharacters() {
        try {
            _characterDao.deleteAllCharacters()
        } catch (e: SQLException) {
            Log.d("Databasefeil", e.toString())
        } catch (e: Exception) {
            Log.d("Databasefeil", e.toString())
        }
    }

    // Sletter et kort
    suspend fun deleteCharacter(character: Character) : Int{
        try {
            return _characterDao.deleteCharacter(character)
        } catch (e : SQLException){
            Log.e("Databasefeil", e.toString())
            return 0
        }

    }
}
