package com.example.eksamen24h.screens.myCharacters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eksamen24h.data.data_classes.Character
import com.example.eksamen24h.data.room.DatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyCharactersViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    fun loadCharactersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = DatabaseRepository.getDatabaseCharacters()
        }
    }
}
