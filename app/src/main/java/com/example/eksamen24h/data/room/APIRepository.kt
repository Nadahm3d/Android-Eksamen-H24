package com.example.eksamen24h.data.room

import android.util.Log
import com.example.eksamen24h.data.data_classes.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIRepository {

    // HENTER FRA API

    // Opprettelse av HTTP-klient
    private val _okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    // Retrofit-objekt (konfigurasjon)
    private val _retrofit = Retrofit.Builder()
        .client(_okHttpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Opprette service-objekt
    private val _rickAndMortyService = _retrofit.create(RickAndMortyService::class.java)

    // Henter karakterer fra API-en
    suspend fun getApiCharacters(): List<Character> {
        return try {
            val response = _rickAndMortyService.getAllCharacters()
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                Log.d("API-feil", "Feil ved henting fra API: ${response.errorBody()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.d("API-feil", e.toString())
            emptyList()
        }
    }
}