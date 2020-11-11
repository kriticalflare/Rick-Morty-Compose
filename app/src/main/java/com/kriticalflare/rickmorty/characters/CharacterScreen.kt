package com.kriticalflare.rickmorty.characters

import android.widget.Toast
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.onSizeChanged
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.kriticalflare.rickmorty.components.CharacterItem
import com.kriticalflare.rickmorty.components.RickMortyAppBar
import com.kriticalflare.rickmorty.data.model.Character
import com.kriticalflare.rickmorty.data.paging.CharactersPaging
import dev.chrisbanes.accompanist.insets.AmbientWindowInsets
import dev.chrisbanes.accompanist.insets.add
import dev.chrisbanes.accompanist.insets.toPaddingValues

@ExperimentalLazyDsl
@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onCharSelect: (Int) -> Unit
) {
    var topAppBarSize by remember { mutableStateOf(0) }

    Surface {
        Box(
            modifier = modifier,
        ) {
            val charactersPaging = remember {
                CharactersPaging()
            }
            val pager = remember {
                Pager(
                    PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = true,
                    )
                ) {
                    charactersPaging.getCharacters()
                }
            }

            val lazyPagingItems: LazyPagingItems<Character> = pager.flow.collectAsLazyPagingItems()

            LazyColumn(
                contentPadding = AmbientWindowInsets.current.systemBars
                    .toPaddingValues(top = false)
                    .add(top = with(DensityAmbient.current) { topAppBarSize.toDp() })
            ) {
                if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                itemsIndexed(lazyPagingItems) { index, item ->
                    if (item != null) {
                        val context = ContextAmbient.current
                        CharacterItem(
                            character = item,
                            onClick = onCharSelect,
                            onSwipe = {
                                Toast.makeText(context, "${it.name}", Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }

                if (lazyPagingItems.loadState.append == LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

            }
            RickMortyAppBar(title = {
                Text("Rick & Morty")
            },
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.9f),
                modifier = Modifier.fillMaxWidth()
                    .onSizeChanged { topAppBarSize = it.height }
            )
        }
    }
}