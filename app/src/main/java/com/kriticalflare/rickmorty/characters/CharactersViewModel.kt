package com.kriticalflare.rickmorty.characters

import androidx.lifecycle.ViewModel
import com.kriticalflare.rickmorty.data.model.Character
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.kriticalflare.rickmorty.data.network.RickMortyClient
import kotlinx.coroutines.launch

class CharactersViewModel(): ViewModel(){

    var charactersList: List<Character> by mutableStateOf(listOf())
        private set

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
}