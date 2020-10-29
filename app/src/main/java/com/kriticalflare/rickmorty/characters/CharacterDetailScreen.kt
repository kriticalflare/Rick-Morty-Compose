package com.kriticalflare.rickmorty.characters

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.kriticalflare.rickmorty.components.RickMortyAppBar
import com.kriticalflare.rickmorty.data.model.CharacterResponse
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun CharacterDetailScreen(charId: Int) {
    val charactersViewModel = viewModel<CharactersViewModel>()

    DisposableEffect(subject = charId ) {
        charactersViewModel.getCharacter(charId)
        onDispose {
            charactersViewModel.charLoading = true
        }
    }

    Scaffold(topBar = { RickMortyAppBar() }) {
        if (charactersViewModel.charLoading) {
            Column(
                Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            val char = charactersViewModel.character
            ScrollableColumn {
                CharacterHeader(char = char)
                CharacterStat(statLabel = "Status", stat = char.status)
                CharacterStat(statLabel = "Species", stat = char.species)
                CharacterStat(statLabel = "Type", stat = char.type)
                CharacterStat(statLabel = "Gender", stat = char.gender)
                CharacterStat(statLabel = "Origin", stat = char.origin.name)
                CharacterStat(statLabel = "Location", stat = char.location.name)
            }
        }
    }
}

@Composable
fun CharacterHeader(char: CharacterResponse) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        CoilImage(data = char.image, fadeIn = true, modifier = Modifier.size(128.dp))
        Spacer(Modifier.weight(1f))
        Column(Modifier.align(Alignment.CenterVertically).padding(start = 8.dp)) {
            Text(char.name, style = MaterialTheme.typography.h3)
        }
        Spacer(Modifier.weight(0.5f))
    }
}

@Composable
fun CharacterStat(statLabel: String, stat: String) {
    Text(
        "$statLabel: ${if (stat.isBlank()) "Unknown" else stat}",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Light)
    )
}