package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kriticalflare.rickmorty.data.model.Character

@Composable
fun CharactersList(modifier: Modifier = Modifier,characters: List<Character>){
    LazyColumnForIndexed(modifier = modifier,items = characters){index, item ->
        CharacterItem(modifier = Modifier.padding(8.dp).fillMaxWidth(),character = characters[index])
    }
}