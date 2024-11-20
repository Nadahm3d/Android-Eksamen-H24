package com.example.eksamen24h.data.room

import com.example.eksamen24h.data.data_classes.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList>
}
