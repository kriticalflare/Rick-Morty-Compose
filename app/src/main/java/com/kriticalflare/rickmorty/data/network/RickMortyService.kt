package com.kriticalflare.rickmorty.data.network

import com.kriticalflare.rickmorty.data.model.CharacterResponse
import com.kriticalflare.rickmorty.data.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyService {

    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): CharactersResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse
}