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

@Composable
fun CharacterItem(
    character: Character,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Assuming you want to use an appropriate size for the UI, consider scaling
    val imageSize = 300.dp // Adjust this based on your UI needs

    Card(
        modifier = Modifier
            .padding(8.dp) // Outer padding for the card within the list
            .width(imageSize) // Set the width of the card to the scaled image size
            .clickable { onClick(character.externalId) }
            .testTag("CharacterCard_${character.name}"),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),

    ) {
        Column {
            AsyncImage(
                model = character.img_url,
                contentDescription = "Image for ${character.name}",
                modifier = Modifier
                    .size(imageSize) // Set the image to be square with the defined size
                    .background(MaterialTheme.colorScheme.surface)
            )
            Column(modifier = Modifier.padding(8.dp)) { // Internal padding for text content
                Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterList(characters: List<Character>, onCharacterClick: (Int) -> Unit) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(characters) { character ->
                CharacterItem(
                    character = character,
                    onClick = onCharacterClick,
                    modifier = Modifier.animateItemPlacement() // Adds simple animations for item placements
                )
            }
        }
    }
}
