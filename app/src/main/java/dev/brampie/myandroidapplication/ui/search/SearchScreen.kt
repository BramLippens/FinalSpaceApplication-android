package dev.brampie.myandroidapplication.ui.search

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.ui.character.overview.CharacterList
import dev.brampie.myandroidapplication.ui.search.SearchViewModel

@Composable
fun SearchScreen(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
) {
    val characterByName by searchViewModel.characterByName.collectAsState()
    var name by remember { mutableStateOf("") }

    Column {
        Row {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Character Name") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { searchViewModel.fetchCharacterByName(name) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.go_to_search)
                )
            }
        }

        // Display the characters info
        if (characterByName.isNotEmpty()) {
            CharacterList(characters = characterByName, onCharacterClick = onClick)
        } else {
            Text("No characters found")
        }
    }
}