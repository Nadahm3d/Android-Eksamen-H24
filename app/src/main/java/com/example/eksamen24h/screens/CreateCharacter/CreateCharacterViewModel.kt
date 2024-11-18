package com.example.eksamen24h.screens.CreateCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CreateCharacterViewModel : ViewModel() {

    // StateFlow som holder listen av karakterer
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    // StateFlow for notifikasjonsmelding
    private val _notificationMessage = MutableStateFlow<String?>(null)
    val notificationMessage: StateFlow<String?> = _notificationMessage

    // Henter karakterer fra databasen
    private fun loadCharactersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = DatabaseRepository.getDatabaseCharacters()
        }
    }

    // Legger til en ny karakter i databasen
    fun insertCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            val newCharacterId = DatabaseRepository.insertCharacter(character)
            if (newCharacterId != -1L) {
                val newCharacter = character.copy(id = newCharacterId.toInt())
                _characters.value += newCharacter
                // Setter notifikasjonsmelding
                _notificationMessage.value = "${character.name} har blitt lagt til!"
            }
        }
    }
}

