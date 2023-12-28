package dev.brampie.myandroidapplication.ui.overviewScreen

import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle

data class OverviewState (
    val currentNews: List<Newsarticle>
)