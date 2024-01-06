package dev.brampie.myandroidapplication.ui.location

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.brampie.myandroidapplication.model.location.Location

/**
 * A Composable function that displays a single location item in a card.
 *
 * @param location The [Location] object to display.
 * @param modifier The modifier to apply to the Composable.
 */
@Composable
fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(400.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = location.img_url,
                contentDescription = "Image for ${location.name}",
                modifier = Modifier
                    .width(400.dp)
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = location.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Type: ${location.type}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

/**
 * A Composable function that displays a list of locations using a LazyColumn.
 *
 * @param locations The list of [Location] objects to display.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationList(
    locations: List<Location>,
    isLandscape: Boolean,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        if(isLandscape){
            LazyRow(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(locations) { location ->
                    LocationItem(location = location)
                }
            }
        }
        else{
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(locations) { location ->
                    LocationItem(
                        location = location,
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }
        }
    }
}