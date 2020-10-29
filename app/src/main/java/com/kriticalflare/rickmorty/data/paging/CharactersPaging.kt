package com.kriticalflare.rickmorty.data.paging

import androidx.paging.PagingSource
import com.kriticalflare.rickmorty.data.model.Character
import com.kriticalflare.rickmorty.data.network.RickMortyClient

class CharactersPaging {

    fun getCharacters(): PagingSource<Int, Character> {
        return object : PagingSource<Int, Character>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
                val pageNumber = params.key ?: 0

                val charactersResponse = RickMortyClient.INSTANCE.getAllCharacters(page = pageNumber)
                val characters = charactersResponse.results

                val prevKey = if (pageNumber > 0) pageNumber - 1 else null
                val nextKey = if (charactersResponse.info.next != null) pageNumber + 1 else null

                return LoadResult.Page(
                    data = characters,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
        }
    }

}
