package com.kriticalflare.rickmorty.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.viewModel
import com.kriticalflare.rickmorty.components.CharactersList
import com.kriticalflare.rickmorty.components.RickMortyAppBar

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onCharSelect: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { RickMortyAppBar() }
    ) {
        val charactersViewModel = viewModel<CharactersViewModel>()
        val characters = charactersViewModel.charactersList
        if (characters.isEmpty()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            CharactersList(modifier = Modifier.fillMaxSize(), characters = characters, onCharSelect)
        }
    }
}