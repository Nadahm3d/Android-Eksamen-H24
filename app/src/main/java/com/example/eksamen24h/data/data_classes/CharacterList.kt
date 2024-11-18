package com.example.eksamen24h.data.data_classes

// Data-klasse som representerer en liste over karakterer fra API-responsen
data class CharacterList(
    val results: List<Character>  // Liste over karakterer hentet fra API
)