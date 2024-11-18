package com.example.eksamen24h.screens.AllCharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.APIRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    // StateFlow som holder listen av karakterer
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    // Initialiserer og henter karakterer fra API
    init {
        getCharactersFromApi()
    }

    // Henter karakterer fra API
    fun getCharactersFromApi() {
        viewModelScope.launch {
            _characters.value = APIRepository.getApiCharacters()
        }
    }
}