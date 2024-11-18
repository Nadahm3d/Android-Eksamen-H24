package com.example.eksamen24h.screens.MyCharacters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eksamen24h.components.CharacterCard
import kotlinx.coroutines.delay

@Composable
fun MyCharactersScreen(myCharactersViewModel: MyCharactersViewModel = viewModel()) {
    val characters by myCharactersViewModel.characters.collectAsState()
    var showMessage by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        myCharactersViewModel.setCharacters()
        delay(3000L)
        showMessage = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Rick and Morty - My Characters",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Text("Antall karakterer: ${characters.size}")

        if (showMessage) {
            Text(
                text = "Henter dine karakterer...",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(characters) { character ->
                    CharacterCard(character = character, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}