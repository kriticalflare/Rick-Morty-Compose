package com.kriticalflare.rickmorty.routing


sealed class Screen(
    val route: String,
    val routeWithoutArgs: String,
    val args: String,
    val title: String
) {
    object CharacterScreen : Screen(
        "CharacterScreen",
        "CharacterScreen",
        "",
        "Rick And Morty"
    )
    object CharacterDetailScreen :
        Screen(
            "CharacterDetailScreen/{id}",
            "CharacterDetailScreen/",
            "{id}",
            "Character"
        )
}