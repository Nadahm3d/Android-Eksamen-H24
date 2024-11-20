package com.example.eksamen24h.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.example.eksamen24h.data.data_classes.Character

@Composable
fun CharacterCard(character: Character, onDelete: (() -> Unit)? = null, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Navn: ${character.name}")
                Text(text = "Art: ${character.species}")
                Text(text = "Status: ${character.status}")
            }

            if (onDelete != null) {
                IconButton(
                    onClick = { onDelete() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Slett karakter"
                    )
                }
            }
        }
    }
}
