package com.example.eksamen24h.screens.deleteCharactersScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eksamen24h.components.CharacterCard
import kotlinx.coroutines.delay

@Composable
fun DeleteCharacterScreen(deleteCharacterViewModel: DeleteCharacterViewModel) {
    val characters by deleteCharacterViewModel.characters.collectAsState()

    var showNotificationDeleted by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf("") }
    var showNoCharacterMessage by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        deleteCharacterViewModel.loadCharactersFromDatabase()
        delay(3000L)
        showNoCharacterMessage = false
    }

    LaunchedEffect(showNotificationDeleted) {
        if (showNotificationDeleted) {
            delay(3000)
            showNotificationDeleted = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Slett karakterer",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Antall karakterer
        Text("Antall karakterer: ${characters.size}")

        // Flytt slettemeldingen her
        if (showNotificationDeleted) {
            Text(
                text = notificationMessage,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = {
                deleteCharacterViewModel.deleteAllCharacters()
                notificationMessage = "Alle karakterer er slettet!"
                showNotificationDeleted = true
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Slett alle karakterer")
        }

        if (characters.isEmpty()) {
            Text(
                text = "Ingen karakterer tilgjengelig for sletting!",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(characters) { character ->
                    CharacterCard(
                        character = character,
                        onDelete = {
                            deleteCharacterViewModel.deleteCharacter(character)
                            notificationMessage = "${character.name} er slettet!"
                            showNotificationDeleted = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }
    }
}



