package dev.brampie.myandroidapplication.ui.character.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.model.character.Character
import dev.brampie.myandroidapplication.network.character.ApiCharacterDetailState

@Composable
fun CharacterDetail(
    character: Character,
    modifier: Modifier = Modifier
) {
    val imageSize = 300.dp

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = character.img_url,
                    contentDescription = "Image for ${character.name}",
                    modifier = Modifier
                        .size(imageSize) // Set the image to be square with the defined size
                        .background(MaterialTheme.colorScheme.surface)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium,

                    )
                DetailItem(label = "Status", value = character.status)
                DetailItem(label = "Species", value = character.species)
                DetailItem(label = "Gender", value = character.gender)
                DetailItem(label = "Hair", value = character.hair)
                DetailItem(label = "Origin", value = character.origin)
                AbilitiesSection(abilities = character.abilities)
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun AbilitiesSection(abilities: List<String>) {
    Text(
        "Abilities:",
        style = MaterialTheme.typography.titleMedium
    )
    for (ability in abilities) {
        Chip(label = ability)
    }
}

@Composable
fun Chip(label: String) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.extraSmall),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shadowElevation = 2.dp
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    modifier: Modifier = Modifier,
    characterDetailViewModel: CharacterDetailViewModel = viewModel(factory = CharacterDetailViewModel.Factory)
) {
    LaunchedEffect(characterId) {
        characterDetailViewModel.getCharacterDetail(characterId)
    }
//    characterDetailViewModel.getCharacterDetail(characterId)

    when(val characterDetailApiState = characterDetailViewModel.characterState.collectAsState().value) {
        is ApiCharacterDetailState.Error -> {
            Text(text = stringResource(R.string.error))
        }
        is ApiCharacterDetailState.Loading -> {
            Text(text = stringResource(R.string.loading))
        }
        is ApiCharacterDetailState.Success -> {
            Log.i("CharacterDetailScreen", "CharacterDetailScreen: ${characterDetailApiState.character}")
                characterDetailApiState.character?.let {
                    CharacterDetail(character = it)
                }

        }
    }
}