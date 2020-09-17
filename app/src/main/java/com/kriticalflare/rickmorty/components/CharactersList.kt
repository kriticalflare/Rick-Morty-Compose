package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.zsoltk.compose.router.BackStack
import com.kriticalflare.rickmorty.data.model.Character
import com.kriticalflare.rickmorty.routing.Router

@Composable
fun CharactersList(modifier: Modifier = Modifier,characters: List<Character>, onCharSelect: (Int) -> Unit){
    LazyColumnForIndexed(modifier = modifier,items = characters){index, item ->
        CharacterItem(
            modifier = Modifier.padding(8.dp).fillMaxWidth().clickable(onClick = {
                onCharSelect(item.id)
            }),
            character = characters[index]
        )
    }
}