package dev.brampie.myandroidapplication.network

import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiNewsarticle (
    val sourceName: String,
    val title: String,
    val image: String,
)

@Serializable
data class ApiNewsarticleResponse(
    val articles: List<Newsarticle>
)


fun Flow<ApiNewsarticleResponse>.asDomainObjects(): Flow<List<Newsarticle>> {
    return map {
        it.asDomainObjects()
    }
}

fun ApiNewsarticleResponse.asDomainObjects(): List<Newsarticle> {
    return articles.map {
        Newsarticle(
            sourceName = it.sourceName,
            title = it.title,
            image = it.image
        )
    }
}