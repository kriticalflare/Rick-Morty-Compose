package com.kriticalflare.rickmorty.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Character>
)