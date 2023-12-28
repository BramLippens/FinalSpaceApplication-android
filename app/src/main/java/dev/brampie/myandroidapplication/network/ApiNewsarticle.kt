package dev.brampie.myandroidapplication.network

import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiNewsarticle (
    val author: String? = null,
    val title: String,
    val urlToImage: String? = null,
)

@Serializable
data class ApiNewsarticleResponse(
    val articles: List<ApiNewsarticle>
)


fun Flow<ApiNewsarticleResponse>.asDomainObjects(): Flow<List<Newsarticle>> {
    return map { it.asDomainObjects() }
}

fun ApiNewsarticleResponse.asDomainObjects(): List<Newsarticle> {
    return articles.map {
        Newsarticle(
            sourceName = it.author?:"" ,
            title = it.title,
            //TODO: add default image
            image = it.urlToImage?:""
        )
    }
}