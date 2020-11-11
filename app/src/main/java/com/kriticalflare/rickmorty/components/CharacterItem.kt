package com.kriticalflare.rickmorty.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import com.kriticalflare.rickmorty.data.model.Character
import dev.chrisbanes.accompanist.coil.CoilImage


private enum class SwipeState {
    NotSwiped,
    FullySwiped,
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (Int) -> Unit,
    onSwipe: (Character) -> Unit
) {

    val width = with(DensityAmbient.current) {
        ConfigurationAmbient.current.screenWidthDp.toDp().toPx()
    }

    val swipeableState = rememberSwipeableState(initialValue = SwipeState.NotSwiped)
    val anchors = mapOf(0f to SwipeState.NotSwiped, width to SwipeState.FullySwiped)

    if (swipeableState.offset.value >= (width / 2)) {
        onSwipe.invoke(character)
        swipeableState.animateTo(SwipeState.NotSwiped)
    }

    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { onClick(character.id) })
            .padding(8.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            ).offsetPx(swipeableState.offset)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            CoilImage(
                data = character.image,
                modifier = Modifier.size(128.dp),
                fadeIn = true,
                loading = { ImageLoadingIndicator() }
            )
            Column(Modifier.padding(8.dp)) {
                Text(character.name, style = MaterialTheme.typography.h5)
                Text(character.status)
                Text(character.species)
            }
        }
    }
}

@Composable
private fun ImageLoadingIndicator() {
    Column(
        modifier = Modifier.size(128.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}