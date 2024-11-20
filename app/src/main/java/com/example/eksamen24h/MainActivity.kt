package com.example.eksamen24h

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.eksamen24h.data.room.DatabaseRepository
import com.example.eksamen24h.navigation.AppNavigation
import com.example.eksamen24h.screens.allCharacters.CharacterViewModel
import com.example.eksamen24h.screens.createCharacter.CreateCharacterViewModel
import com.example.eksamen24h.screens.deleteCharactersScreen.DeleteCharacterViewModel
import com.example.eksamen24h.screens.myCharacters.MyCharactersViewModel
import com.example.eksamen24h.ui.theme.Eksamen24HTheme

class MainActivity : ComponentActivity() {
    private val charactersViewModel: CharacterViewModel by viewModels()
    private val createCharacterViewModel: CreateCharacterViewModel by viewModels()
    private val myCharactersViewModel: MyCharactersViewModel by viewModels()
    private val deleteCharacterViewModel: DeleteCharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DatabaseRepository.initializeDatabase(applicationContext)

        charactersViewModel.getCharactersFromApi()

        setContent {
            Eksamen24HTheme {
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





















