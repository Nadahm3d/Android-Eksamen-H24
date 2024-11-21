package com.example.eksamen24h.screens.createCharacter

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

    var showError by remember { mutableStateOf(false) }
    var notificationVisible by remember { mutableStateOf(false) }
    var notificationMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        createCharacterViewModel.loadCharactersFromDatabase()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lag ny karakter",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Antall karakterer: ${characters.size}")


        if (showError) {
            Text(
                text = "Alle felt må fylles ut",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Navn") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = species,
            onValueChange = { species = it },
            label = { Text("Art") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                if (name.isBlank() || species.isBlank() || status.isBlank()) {
                    showError = true
                } else {
                    showError = false

                    val newCharacter = Character(
                        name = name,
                        species = species,
                        status = status,
                        image = ""
                    )
                    createCharacterViewModel.insertCharacter(newCharacter)

                    notificationMessage = "${newCharacter.name} er lagt til!"
                    notificationVisible = true

                    name = ""
                    species = ""
                    status = ""
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Lagre ny karakter")
        }


        if (notificationVisible) {
            Text(
                text = notificationMessage,
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            LaunchedEffect(notificationVisible) {
                delay(3000L)
                notificationVisible = false
            }
        }
    }
}

