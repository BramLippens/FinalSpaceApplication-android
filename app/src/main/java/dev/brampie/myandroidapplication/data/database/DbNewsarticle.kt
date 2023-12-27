package dev.brampie.myandroidapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import java.util.UUID

@Entity(tableName = "newsarticles")
data class DbNewsarticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sourceName: String,
    val title: String,
    val image: String
)
fun DbNewsarticle.asDomainNewsarticle() : Newsarticle = Newsarticle(
    sourceName = sourceName,
    title = title,
    image = image
)

fun Newsarticle.asDbNewsarticle() : DbNewsarticle = DbNewsarticle(
    //TODO: Fix this
    id = UUID.randomUUID().hashCode(),
    sourceName = sourceName,
    title = title,
    image = image
)



fun List<DbNewsarticle>.asDomainNewsarticles() = map { it.asDomainNewsarticle() }