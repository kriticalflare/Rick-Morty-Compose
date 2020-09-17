package com.kriticalflare.rickmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import com.kriticalflare.rickmorty.characters.CharactersViewModel
import com.kriticalflare.rickmorty.ui.RickmortyTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kriticalflare.rickmorty.characters.CharacterScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val charactersViewModel by viewModels<CharactersViewModel>()
        setContent {
            RickmortyTheme {
                CharacterScreen(Modifier.fillMaxSize(),characters = charactersViewModel.charactersList)
            }
        }
    }
}
