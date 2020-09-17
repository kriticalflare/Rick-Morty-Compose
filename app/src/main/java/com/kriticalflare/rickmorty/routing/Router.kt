package com.kriticalflare.rickmorty.routing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.zsoltk.compose.router.Router
import com.kriticalflare.rickmorty.characters.CharacterDetailScreen
import com.kriticalflare.rickmorty.characters.CharacterScreen

interface Router {

    sealed class Routing {
        object Home : Routing()
        class CharDetailScreen(val id: Int) : Routing()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: Routing = Routing.Home) {
            Router(defaultRouting = defaultRouting) { backStack ->
                when (val routing = backStack.last()) {
                    is Routing.Home -> CharacterScreen(
                        Modifier.fillMaxSize(),
                        onCharSelect = { id ->
                            backStack.push(Routing.CharDetailScreen(id))
                        })
                    is Routing.CharDetailScreen -> {
                        CharacterDetailScreen(routing.id)
                    }
                }
            }
        }
    }
}