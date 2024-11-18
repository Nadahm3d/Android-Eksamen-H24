package com.example.eksamen24h.screens.CreateCharacter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import com.example.eksamen24h.data.data_classes.Character



@Composable
fun CreateCharacterScreen(databaseCharacterViewModel: CreateCharacterViewModel) {
    // Henter listen med karakterer fra databasen (ikke brukt direkte her)
    val characters by databaseCharacterViewModel.characters.collectAsState()

    // State for å lagre inputfelter
    var name by remember { mutableStateOf("") }
    var species by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var showNotification by remember { mutableStateOf(false) } // Kontroll for visning av notifikasjon
    var showError by remember { mutableStateOf(false) } // Kontroll for visning av feilmelding når b ruker har skrevet en karakter som allerede er laget

    // Viser notifikasjon om at karakterer er lagret i 2 sekunder før den skjules
    LaunchedEffect(showNotification) {
        if (showNotification) {
            delay(2000)
            showNotification = false
        }
    }

    // Viser feilmelding om at karakter allerede finnes i 2 sekunder før den skjules
    LaunchedEffect(showError) {
        if (showError) {
            delay(2000)
            showError = false
        }
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
                // Sjekk om karakterens navn allerede finnes i listen
                val characterExists = characters.any { it.name == name }

                if (characterExists) {
                    showError = true // Vis feilmelding
                } else {
                    val newCharacter = Character(
                        name = name,
                        species = species,
                        status = status,
                        image = "" // Bildet er tomt som standard fordi bruker kan ikke legge inn bilde
                    )
                    databaseCharacterViewModel.insertCharacter(newCharacter) // Setter inn ny karakter i databasen
                    name = "" // Tilbakestill inputfeltene
                    species = ""
                    status = ""
                    showNotification = true // Aktiverer notifikasjon om at karakter er laget
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Lagre ny karakter")
        }

        // Viser en melding når en karakter er lagret
        if (showNotification) {
            Text(
                text = "Karakter lagret!",
                color = Color.Green,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        // Viser en feilmelding når karakternavnet allerede finnes
        if (showError) {
            Text(
                text = "Karakter med dette navnet finnes allerede!",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
