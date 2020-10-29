package com.kriticalflare.rickmorty.characters

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.kriticalflare.rickmorty.data.model.CharacterResponse
import com.kriticalflare.rickmorty.data.network.RickMortyClient
import kotlinx.coroutines.launch

class CharactersViewModel(): ViewModel(){

    var charLoading: Boolean by mutableStateOf(true)
    lateinit var character: CharacterResponse


    fun getCharacter(id: Int){
        viewModelScope.launch {
            charLoading = true
            val response = RickMortyClient.INSTANCE.getCharacter(id)
            character = response
            charLoading = false
        }
    }
}