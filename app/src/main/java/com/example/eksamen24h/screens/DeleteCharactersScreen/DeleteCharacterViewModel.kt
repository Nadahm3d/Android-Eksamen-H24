package com.example.yourapp.screens.delete_character


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class DeleteCharacterViewModel : ViewModel() {
    // _characters is a private state for the ViewModel
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    // characters is the state shared with the Screen
    val characters = _characters.asStateFlow()

    fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = DatabaseRepository.getDatabaseCharacters() // Fetch characters from the database
            Log.d("DeleteCharacterViewModel", "Characters fetched: ${_characters.value.size}")
        }
    }



    // 2. Delete a character
    fun deleteCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            val numberDeleted = DatabaseRepository.deleteCharacter(character)
            if (numberDeleted == 1) {
                val currentList = _characters.value
                val afterDeleteList = currentList.filter { it != character }
                _characters.value = afterDeleteList
            }
        }
    }

    // Sletter alle karakterer fra databasen og oppdaterer listen
    fun deleteAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseRepository.deleteAllCharacters()
            _characters.value = emptyList() // Tømmer listen i UI etter sletting
        }
    }
}