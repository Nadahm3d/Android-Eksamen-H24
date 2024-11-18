package com.example.eksamen24h.screens.AllCharacters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.rememberImagePainter
import com.example.eksamen24h.data.data_classes.Character
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterItem(character: Character) {
    Column {
        Text(text = character.id.toString())  // Viser karakterens ID
        Text(text = character.name)            // Viser karakterens navn
        Text(text = character.species)         // Viser karakterens art
        Text(text = character.status)          // Viser karakterens status

        // Viser bildet av karakteren
        Image(
            painter = rememberImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier.size(100.dp) // Juster størrelsen etter behov
        )
    }
}