package com.example.eksamen24h.data.data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // ID for karakteren, auto-generert
    val name: String,
    val status: String,
    val species: String,
    val image: String
)
