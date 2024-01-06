package dev.brampie.myandroidapplication.ui.character.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.brampie.myandroidapplication.model.character.Character

/**
 * A Composable function that displays a single character item in a card.
 *
 * @param character The [Character] object to display.
 * @param onClick The click event handler for the character item.
 * @param modifier The modifier to apply to the Composable.
 */
@Composable
fun CharacterItem(
    character: Character,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean
) {
    val imageSize = if(isLandscape) 200.dp else 300.dp

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(imageSize)
            .clickable { onClick(character.id) }
            .testTag("CharacterCard_${character.name}"),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),

    ) {
        Column {
            AsyncImage(
                model = character.img_url,
                contentDescription = "Image for ${character.name}",
                modifier = Modifier
                    .size(imageSize)
                    .background(MaterialTheme.colorScheme.surface)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = character.name.truncate(16), style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                if (!isLandscape){
                    Text(text = "Species: ${character.species.truncate(20)}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Status: ${character.status.truncate(16)}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

fun String.truncate(maxLength: Int): String {
    return if (this.length > maxLength) {
        this.substring(0, maxLength) + "..."
    } else {
        this
    }
}

/**
 * A Composable function that displays a list of characters using a LazyColumn.
 *
 * @param characters The list of [Character] objects to display.
 * @param onCharacterClick The click event handler for character items.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterList(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    isLandscape: Boolean = false
) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        if(isLandscape){
            LazyRow(modifier = Modifier
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.background)
            ){
                items(characters) { character ->
                    CharacterItem(
                        character = character,
                        onClick = onCharacterClick,
                        modifier = Modifier.animateItemPlacement(),
                        isLandscape = isLandscape
                    )
                }
            }
        }
        else{
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(characters) { character ->
                    CharacterItem(
                        character = character,
                        onClick = onCharacterClick,
                        modifier = Modifier.animateItemPlacement(),
                        isLandscape = isLandscape
                    )
                }
            }
        }
    }
}
