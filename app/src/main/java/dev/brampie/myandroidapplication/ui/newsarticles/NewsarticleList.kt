package dev.brampie.myandroidapplication.ui.newsarticles

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle

@Composable
fun NewsarticleList(newsarticles: List<Newsarticle>) {
    LazyColumn{
        items(newsarticles) { newsarticle ->
            NewsarticleItem(newsarticle)
        }
    }
}

@Composable
fun NewsarticleItem(newsarticle: Newsarticle, modifier: Modifier = Modifier) {
    Text(newsarticle.title)
    Divider(modifier.height(2.dp).fillMaxWidth())
}
