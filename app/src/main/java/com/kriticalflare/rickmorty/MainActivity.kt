package com.kriticalflare.rickmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.kriticalflare.rickmorty.characters.CharacterDetailScreen
import com.kriticalflare.rickmorty.characters.CharacterScreen
import com.kriticalflare.rickmorty.routing.Screen
import com.kriticalflare.rickmorty.ui.RickmortyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {

    @ExperimentalLazyDsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            ProvideWindowInsets {
                RickmortyTheme {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CharacterScreen.route
                    ) {
                        composable(Screen.CharacterScreen.route) {
                            CharacterScreen(
                                Modifier.fillMaxSize(),
                                onCharSelect = { id ->
                                    navController.navigate(
                                        Screen.CharacterDetailScreen.routeWithoutArgs + "$id",
                                    )
                                })
                        }

                        composable(
                            Screen.CharacterDetailScreen.route,
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { navBackStackEntry ->
                            CharacterDetailScreen(navBackStackEntry.arguments?.getInt("id")!!)
                        }
                    }
                }
            }
        }
    }
}
