package com.example.eksamen24h.data.room

import com.example.eksamen24h.data.data_classes.CharacterList
import retrofit2.Response
import retrofit2.http.GET

// Interface for Rick and Morty API-tjeneste
interface RickAndMortyService {

    // Metode for å hente alle karakterer fra API-et
    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList> // Returnerer en respons med en liste av karakterer
}