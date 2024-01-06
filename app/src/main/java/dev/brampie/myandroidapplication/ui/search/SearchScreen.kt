package dev.brampie.myandroidapplication.ui.search

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.ui.character.overview.CharacterList
import dev.brampie.myandroidapplication.ui.search.SearchViewModel

/**
 * A Composable function that displays a search screen for characters.
 *
 * @param onClick The click event handler for selecting a character.
 * @param modifier The modifier to apply to the Composable.
 * @param searchViewModel The ViewModel responsible for character search.
 */
@Composable
fun SearchScreen(
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    isLandscape: Boolean,
    searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory)
) {
    val characterByName by searchViewModel.characterByName.collectAsState()
    var name by remember { mutableStateOf("") }

    val localFocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = {
                    name = it
                    searchViewModel.onQueryChanged(it)
                },
                label = { Text("Character Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    localFocusManager.clearFocus()
                    searchViewModel.fetchCharacterByName(name)
                }),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { name = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                    }
                }
            )
        }

        if (characterByName.isNotEmpty()) {
            CharacterList(characters = characterByName, isLandscape = isLandscape, onCharacterClick = onClick)
        } else {
            Text(
                "No characters found",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}