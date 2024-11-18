package com.example.eksamen24h

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.eksamen24h.data.room.APIRepository
import com.example.eksamen24h.data.room.DatabaseRepository
import com.example.eksamen24h.navigation.AppNavigation
import com.example.eksamen24h.screens.AllCharacters.CharacterViewModel
import com.example.eksamen24h.screens.CreateCharacter.CreateCharacterViewModel
import com.example.eksamen24h.screens.MyCharacters.MyCharactersViewModel
import com.example.eksamen24h.ui.theme.Eksamen24HTheme
import com.example.yourapp.screens.delete_character.DeleteCharacterViewModel

class MainActivity : ComponentActivity() {
    // ViewModels for å håndtere UI-relatert data
    private val charactersViewModel: CharacterViewModel by viewModels()
    private val createCharacterViewModel: CreateCharacterViewModel by viewModels()
    private val myCharactersViewModel: MyCharactersViewModel by viewModels()
    private val deleteCharacterViewModel: DeleteCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialiserer Room-databasen
        DatabaseRepository.initializeDatabase(applicationContext)

        // Henter karakterer fra API-en i ViewModel
        charactersViewModel.getCharactersFromApi()

        // Setter innholdet for aktiviteten
        setContent {
            Eksamen24HTheme {
                // Setter opp navigasjon for appen
                AppNavigation(
                    charactersViewModel,
                    createCharacterViewModel,
                    myCharactersViewModel,
                    deleteCharacterViewModel
                )
            }
        }
    }
}




















