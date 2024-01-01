package dev.brampie.myandroidapplication.network.character

import dev.brampie.myandroidapplication.model.character.CharacterDetail
import kotlinx.serialization.Serializable

@Serializable
data class ApiCharacterDetail(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender :String,
    val hair: String,
    val origin: String,
    val img_url: String
)

fun ApiCharacterDetail.asDomainObject(): CharacterDetail{
    return let{
        CharacterDetail(
            externalId = it.id,
            name = it.name,
            status = it.status,
            gender = it.gender,
            species = it.species,
            hair = it.hair,
            origin = it.origin,
            img_url = it.img_url
        )
    }
}