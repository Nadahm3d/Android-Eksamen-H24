package com.example.eksamen24h.screens.createCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CreateCharacterViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val character = _characters.asStateFlow()

    private val _notificationMessage = MutableStateFlow<String?>(null)
    val notificationMessage: StateFlow<String?> = _notificationMessage

    fun loadCharactersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = DatabaseRepository.getDatabaseCharacters()
        }
    }

    fun insertCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            val newCharacterId = DatabaseRepository.insertCharacter(character)
            if (newCharacterId != -1L) {
                val newCharacter = character.copy(id = newCharacterId.toInt())
                _characters.value += newCharacter
                _notificationMessage.value = "${character.name} har blitt lagt til!"
            }
        }
    }
}

