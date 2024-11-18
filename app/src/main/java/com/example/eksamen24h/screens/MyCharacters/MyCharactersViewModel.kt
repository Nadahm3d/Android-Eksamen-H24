package com.example.eksamen24h.screens.MyCharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyCharactersViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    init {
        // Henter karakterene fra databasen når ViewModelen blir initialisert
        loadCharactersFromDatabase()
    }

    // Funksjon for å hente karakterer fra databasen
    private fun loadCharactersFromDatabase() {
        viewModelScope.launch {
            _characters.value = DatabaseRepository.getDatabaseCharacters()
        }
    }
}
