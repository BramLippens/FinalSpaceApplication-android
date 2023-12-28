package dev.brampie.myandroidapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import java.util.UUID

@Entity(tableName = "newsarticles")
data class DbNewsarticle(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val author: String,
    val title: String,
    val image: String,
    val publishedAt: String
)
fun DbNewsarticle.asDomainNewsarticle() : Newsarticle = Newsarticle(
    author = author,
    title = title,
    image = image,
    publishedAt = publishedAt
)

fun Newsarticle.asDbNewsarticle() : DbNewsarticle = DbNewsarticle(
    //TODO: Fix this
    id = UUID.randomUUID(),
    author = author,
    title = title,
    image = image,
    publishedAt = publishedAt
)



fun List<DbNewsarticle>.asDomainNewsarticles() = map { it.asDomainNewsarticle() }