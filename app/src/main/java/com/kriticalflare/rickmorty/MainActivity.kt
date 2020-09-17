package com.kriticalflare.rickmorty

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.platform.setContent
import com.kriticalflare.rickmorty.characters.CharactersViewModel
import com.kriticalflare.rickmorty.ui.RickmortyTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.kriticalflare.rickmorty.characters.CharacterScreen
import com.kriticalflare.rickmorty.routing.Router

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Providers(
                AmbientBackPressHandler provides backPressHandler
            ) {
                RickmortyTheme {
                    Router.Content()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }
}
