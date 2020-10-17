package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kriticalflare.rickmorty.data.model.Character

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    onCharSelect: (Int) -> Unit
) {
    LazyColumnForIndexed(modifier = modifier, items = characters) { index, item ->
        CharacterItem(
            modifier = Modifier.padding(8.dp),
            character = characters[index],
            onClick = onCharSelect
        )
    }
}