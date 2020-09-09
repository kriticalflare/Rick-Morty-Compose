package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.kriticalflare.rickmorty.data.model.Character
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade

@Composable
fun CharacterItem(modifier: Modifier = Modifier, character: Character){
    Card(modifier) {
        Row(modifier) {
            CoilImageWithCrossfade(data = character.image)
            Column(modifier){
                Text(character.name,style = MaterialTheme.typography.h5)
                Text(character.status)
                Text(character.species)
            }
        }
    }
}