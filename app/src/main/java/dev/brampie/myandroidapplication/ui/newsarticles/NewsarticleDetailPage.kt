package dev.brampie.myandroidapplication.ui.newsarticles

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.ui.overviewScreen.NewsOverviewViewModel

@Composable
fun NewsarticleDetailScreen(
    articleId: Int,
    viewModel: NewsOverviewViewModel = viewModel(factory = NewsOverviewViewModel.Factory)
) {
    Text(text = "THIS IS A TEST $articleId")
}