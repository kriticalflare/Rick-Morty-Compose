package com.kriticalflare.rickmorty.characters

import androidx.lifecycle.ViewModel
import com.kriticalflare.rickmorty.data.model.Character
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.kriticalflare.rickmorty.data.model.CharacterResponse
import com.kriticalflare.rickmorty.data.network.RickMortyClient
import kotlinx.coroutines.launch

class CharactersViewModel(): ViewModel(){

    var charactersList: List<Character> by mutableStateOf(listOf())
        private set

    var charLoading: Boolean by mutableStateOf(true)
    lateinit var character: CharacterResponse

    init {
        viewModelScope.launch {
            getAllCharacters()
        }
    }

    private suspend fun getAllCharacters(){
        val response = RickMortyClient.INSTANCE.getAllCharacters()
        val result = response.results
        charactersList = result
    }

    fun getCharacter(id: Int){
        viewModelScope.launch {
            charLoading = true
            val response = RickMortyClient.INSTANCE.getCharacter(id)
            character = response
            charLoading = false
        }
    }
}