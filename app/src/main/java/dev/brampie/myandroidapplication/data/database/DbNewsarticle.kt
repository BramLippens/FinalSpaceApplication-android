package dev.brampie.myandroidapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import java.util.UUID

@Entity(tableName = "newsarticles")
data class DbNewsarticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val title: String,
    val description: String,
    val content : String,
    val image: String,
    val publishedAt: String
)
fun DbNewsarticle.asDomainNewsarticle() : Newsarticle = Newsarticle(
    internalId = id,
    author = author,
    title = title,
    image = image,
    description = description,
    content = content,
    publishedAt = publishedAt
)

fun Newsarticle.asDbNewsarticle() : DbNewsarticle = DbNewsarticle(
    author = author,
    title = title,
    image = image,
    description = description,
    content = content,
    publishedAt = publishedAt
)



fun List<DbNewsarticle>.asDomainNewsarticles() = map { it.asDomainNewsarticle() }