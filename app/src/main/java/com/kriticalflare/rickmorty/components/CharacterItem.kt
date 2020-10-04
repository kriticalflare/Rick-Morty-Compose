package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kriticalflare.rickmorty.data.model.Character
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade

@Composable
fun CharacterItem(modifier: Modifier = Modifier, character: Character) {
    Card(modifier) {
        Row(modifier) {
            CoilImageWithCrossfade(
                data = character.image,
                modifier = Modifier.size(128.dp),
                loading = { ImageLoadingIndicator() })
            Column(modifier) {
                Text(character.name, style = MaterialTheme.typography.h5)
                Text(character.status)
                Text(character.species)
            }
        }
    }
}

@Composable
private fun ImageLoadingIndicator() {
    Column(
        modifier = Modifier.size(128.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}