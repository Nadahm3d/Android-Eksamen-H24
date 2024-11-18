package com.example.eksamen24h.screens.CreateCharacter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eksamen24h.data.data_classes.Character
import kotlinx.coroutines.delay


@Composable
fun CreateCharacterScreen(createCharacterViewModel: CreateCharacterViewModel) {
    val characters by createCharacterViewModel.character.collectAsState()

    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    // Tilstand for notifikasjon
    var notificationVisible by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf("") }

    // Hent karakterer fra databasen når skjermen opprettes
    LaunchedEffect(Unit) {
        createCharacterViewModel.loadCharactersFromDatabase()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Overskrift for skjermen
        Text(
            text = "Create a New Character",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Viser antall lagrede karakterer
        Text("Antall karakterer: ${characters.size}")

        // Inputfelt for navn
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Navn") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Inputfelt for art
        TextField(
            value = species,
            onValueChange = { species = it },
            label = { Text("Art") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Inputfelt for status
        TextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Knapp for å lagre ny karakter
        Button(
            onClick = {
                val newCharacter = Character(
                    name = name,
                    species = species,
                    status = status,
                    image = "" // Bildet er tomt som standard
                )
                createCharacterViewModel.insertCharacter(newCharacter) // Setter inn ny karakter i databasen

                // Vise notifikasjon
                notificationMessage = "${newCharacter.name} er lagt til!"
                notificationVisible = true

                // Tilbakestill inputfeltene
                name = ""
                species = ""
                status = ""
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Lagre ny karakter")
        }

        // Vise notifikasjon hvis den er synlig
        if (notificationVisible) {
            Text(
                text = notificationMessage,
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            // La meldingen være synlig i 2 sekunder
            LaunchedEffect(notificationVisible) {
                delay(2000L)
                notificationVisible = false
            }
        }
    }
}




