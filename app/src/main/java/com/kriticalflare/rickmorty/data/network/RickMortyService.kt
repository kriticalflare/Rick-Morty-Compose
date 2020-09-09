package com.kriticalflare.rickmorty.data.network

import com.kriticalflare.rickmorty.data.model.CharactersResponse
import retrofit2.http.GET

interface RickMortyService {

    @GET("character/")
    suspend fun getAllCharacters(): CharactersResponse
}