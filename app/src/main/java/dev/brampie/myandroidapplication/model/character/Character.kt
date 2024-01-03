package dev.brampie.myandroidapplication.model.character

class Character (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val hair: String,
    val origin: String,
    val abilities: List<String>,
    val img_url: String
)