package com.example.eksamen24h.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.eksamen24h.screens.allCharacters.CharacterScreen
import com.example.eksamen24h.screens.createCharacter.CreateCharacterScreen
import com.example.eksamen24h.screens.deleteCharactersScreen.DeleteCharacterScreen
import com.example.eksamen24h.screens.allCharacters.CharacterViewModel
import com.example.eksamen24h.screens.createCharacter.CreateCharacterViewModel
import com.example.eksamen24h.screens.deleteCharactersScreen.DeleteCharacterViewModel
import com.example.eksamen24h.screens.myCharacters.MyCharactersScreen
import com.example.eksamen24h.screens.myCharacters.MyCharactersViewModel


@Composable
fun AppNavigation(
    charactersViewModel: CharacterViewModel,
    createCharacterViewModel: CreateCharacterViewModel,
    myCharactersViewModel: MyCharactersViewModel,
    deleteCharacterViewModel: DeleteCharacterViewModel
) {
    val navController = rememberNavController()
    var selectedItemIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedItemIndex == 0,
                    onClick = {
                        selectedItemIndex = 0
                        navController.navigate("characters")
                    },
                    icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null) },
                    label = {
                        Text(
                            "Karakterer",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 1,
                    onClick = {
                        selectedItemIndex = 1
                        navController.navigate("create_character")
                    },
                    icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = null) },
                    label = {
                        Text(
                            "Opprett Karakter",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 2,
                    onClick = {
                        selectedItemIndex = 2
                        navController.navigate("my_characters")
                    },
                    icon = { Icon(imageVector = Icons.Filled.Person, contentDescription = null) },
                    label = {
                        Text(
                            "Mine Karakterer",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 3,
                    onClick = {
                        selectedItemIndex = 3
                        navController.navigate("delete_characters")
                    },
                    icon = { Icon(imageVector = Icons.Filled.Delete, contentDescription = null) },
                    label = {
                        Text(
                            "Slett Karakterer",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "characters",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("characters") {
                CharacterScreen(charactersViewModel)
            }
            composable("create_character") {
                CreateCharacterScreen(createCharacterViewModel)
            }
            composable("my_characters") {
                MyCharactersScreen(myCharactersViewModel)
            }
            composable("delete_characters") {
                DeleteCharacterScreen(deleteCharacterViewModel)
            }
        }
    }
}
