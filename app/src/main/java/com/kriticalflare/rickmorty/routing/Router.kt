package com.kriticalflare.rickmorty.routing


sealed class Screen(val title: String) {
    object CharacterScreen : Screen("CharacterScreen")
    object CharacterDetailScreen : Screen("CharacterDetailScreen")
}