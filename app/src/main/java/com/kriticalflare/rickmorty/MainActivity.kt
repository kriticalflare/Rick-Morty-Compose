package com.kriticalflare.rickmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.core.os.bundleOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.kriticalflare.rickmorty.characters.CharacterDetailScreen
import com.kriticalflare.rickmorty.characters.CharacterScreen
import com.kriticalflare.rickmorty.routing.Screen
import com.kriticalflare.rickmorty.ui.RickmortyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickmortyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.CharacterScreen.title
                ) {
                    composable(Screen.CharacterScreen.title) {
                        CharacterScreen(
                            Modifier.fillMaxSize(),
                            onCharSelect = { id ->
                                navController.navigate(
                                    Screen.CharacterDetailScreen.title,
                                    bundleOf("id" to id)
                                )
                            })
                    }

                    composable(Screen.CharacterDetailScreen.title) { navBackStackEntry ->
                        CharacterDetailScreen(navBackStackEntry.arguments?.getInt("id")!!)
                    }
                }
            }
        }
    }
}
