package dev.brampie.myandroidapplication.data.database.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.brampie.myandroidapplication.model.character.Character

@Entity(tableName = "characters")
data class DbCharacter(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val hair: String,
    val origin: String,
    val abilities: String,
    val img_url: String
)

fun DbCharacter.asDomainCharacter() : Character = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    hair = hair,
    origin = origin,
    abilities = abilities.split(";"),
    img_url = img_url
)

fun Character.asDbCharacter() : DbCharacter = DbCharacter(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    hair = hair,
    origin = origin,
    abilities = abilities.joinToString (separator = "; "),
    img_url = img_url
)

fun List<DbCharacter>.asDomainCharacters() = map { it.asDomainCharacter() }