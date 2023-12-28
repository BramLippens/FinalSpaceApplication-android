package dev.brampie.myandroidapplication.ui.newsarticles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.brampie.myandroidapplication.R
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Implement click action, e.g., navigation */ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = newsarticle.image,
            contentDescription = "Image for ${newsarticle.title}",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // You can adjust the height as necessary
                .aspectRatio(1.78f), // 16:9 aspect ratio; adjust as necessary
            //TODO: Fix this
//            error = painterResource(R.drawable.ic_error), // Replace with your actual error drawable resource
//            placeholder = painterResource(R.drawable.ic_placeholder) // Replace with your actual placeholder drawable resource
        )
        Spacer(modifier = Modifier.height(8.dp)) // Space between image and title
        Text(text = newsarticle.title, style = MaterialTheme.typography.titleMedium) // Smaller title text
        Spacer(modifier = Modifier.height(4.dp)) // Space between title and author
        Text(text = "By ${newsarticle.author}", style = MaterialTheme.typography.bodySmall) // Author text
        Text(text = newsarticle.publishedAt, style = MaterialTheme.typography.bodySmall) // Published date text
    }
}
