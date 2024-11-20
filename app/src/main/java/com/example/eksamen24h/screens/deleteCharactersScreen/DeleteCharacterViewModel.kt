package com.example.eksamen24h.screens.deleteCharactersScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DeleteCharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters = _characters.asStateFlow()

    fun loadCharactersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = DatabaseRepository.getDatabaseCharacters()
        }
    }

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

    fun deleteAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            DatabaseRepository.deleteAllCharacters()
            _characters.value = emptyList()
        }
    }
}
