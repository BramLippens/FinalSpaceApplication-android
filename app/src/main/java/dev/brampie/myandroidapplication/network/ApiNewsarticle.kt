package dev.brampie.myandroidapplication.network

import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class ApiNewsarticle (
    val author: String? = null,
    val title: String,
    val urlToImage: String? = null,
    val description: String? = null,
    val content : String? = null,
    val publishedAt: String? = null
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
            author = it.author?:"Unknown",
            title = it.title,
            image = it.urlToImage?:"https://picsum.photos/200/300?random=${Random.nextInt(0, 100)}",
            description = it.description?:"Empty",
            content = it.content?:"Empty",
            publishedAt = it.publishedAt?:"Unknown"
        )
    }
}