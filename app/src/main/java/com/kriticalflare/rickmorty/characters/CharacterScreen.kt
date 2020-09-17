package com.kriticalflare.rickmorty.characters

import androidx.compose.foundation.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kriticalflare.rickmorty.components.CharactersList
import com.kriticalflare.rickmorty.components.RickMortyAppBar
import com.kriticalflare.rickmorty.data.model.Character

@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier.fillMaxSize(), characters: List<Character>
) {
    Scaffold(
        modifier = modifier,
        topBar = { RickMortyAppBar() }
    ) {
        if (characters.isEmpty()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            CharactersList(modifier = Modifier.fillMaxSize(), characters = characters)
        }
    }
}