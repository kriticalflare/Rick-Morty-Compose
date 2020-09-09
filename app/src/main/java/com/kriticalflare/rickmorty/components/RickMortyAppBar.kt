package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.kriticalflare.rickmorty.ui.RickmortyTheme

@Composable
fun RickMortyAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text("Rick & Morty")
        }
    )
}

@Preview
@Composable
fun RickMortyAppBarPreview(){
    RickmortyTheme {
        RickMortyAppBar()
    }
}