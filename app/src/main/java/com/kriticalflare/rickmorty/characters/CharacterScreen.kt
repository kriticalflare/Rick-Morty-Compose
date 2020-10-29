package com.kriticalflare.rickmorty.characters

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.ExperimentalLazyDsl
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@ExperimentalLazyDsl
@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onCharSelect: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { RickMortyAppBar() }
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

        LazyColumn {
            if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                item{
                    Column(modifier = Modifier.fillParentMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                    }
                }
            }

            itemsIndexed(lazyPagingItems) { index, item ->
                if (item != null) {
                    CharacterItem(
                        modifier = Modifier.padding(8.dp),
                        character = item,
                        onClick = onCharSelect
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
    }
}