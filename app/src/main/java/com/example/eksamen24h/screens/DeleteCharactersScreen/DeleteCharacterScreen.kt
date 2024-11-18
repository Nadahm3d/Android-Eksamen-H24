
package com.example.eksamen24h.screens.DeleteCharactersScreen


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
import com.example.yourapp.screens.delete_character.DeleteCharacterViewModel
import kotlinx.coroutines.delay

@Composable
fun DeleteCharacterScreen(deleteCharacterViewModel: DeleteCharacterViewModel) {
    val characters by deleteCharacterViewModel.characters.collectAsState()
    var showNotificationDeleted by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf("") }
    var showNoCharacterMessage by remember { mutableStateOf(true) }

    // Hent karakterer fra databasen når skjermen vises
    LaunchedEffect(Unit) {
        deleteCharacterViewModel.setCharacters()
        delay(3000L)
        showNoCharacterMessage = false
    }

    // Håndterer visning av slettemelding
    LaunchedEffect(showNotificationDeleted) {
        if (showNotificationDeleted) {
            // Vent i 2 sekunder
            delay(2000)
            // Sett showNotification tilbake til false
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
            "Delete Characters",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Antall karakterer: ${characters.size}")

        // Vis feilmelding hvis databasen er tom
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

            // Knapp for å slette alle karakterer
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
        }

        // Viser melding når karakterer slettes
        if (showNotificationDeleted) {
            Text(
                text = notificationMessage,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
