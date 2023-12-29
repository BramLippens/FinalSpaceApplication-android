package dev.brampie.myandroidapplication.ui.overviewScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.brampie.myandroidapplication.R
import dev.brampie.myandroidapplication.network.NewsApiState
import dev.brampie.myandroidapplication.ui.newsarticles.NewsarticleList

@Composable
fun NewsOverview(
    onNewsarticleClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    newsOverviewViewModel: NewsOverviewViewModel = viewModel(factory = NewsOverviewViewModel.Factory)
) {
    val newsApiState = newsOverviewViewModel.newsApiState
    val uiNewsListState by newsOverviewViewModel.uiNewsListState.collectAsState()

    when(newsApiState) {
        is NewsApiState.Error -> {
            Text(text = stringResource(R.string.error_loading_news_from_api))
        }

        is NewsApiState.Loading -> {
            Text(text = stringResource(R.string.loading_news_from_api))
        }

        is NewsApiState.Success -> {
            NewsarticleList(uiNewsListState.newsList, onNewsarticleClick)
        }
    }
}