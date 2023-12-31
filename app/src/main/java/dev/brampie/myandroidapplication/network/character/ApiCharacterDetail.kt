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
    val abilities: List<ApiAbility>,
    val img_url: String
)

@Serializable
data class ApiAbility(
    val name: String
)

@Serializable
data class ApiCharacterDetailResponse(
    val character: ApiCharacterDetail
)

fun ApiCharacterDetailResponse.asDomainObject(): CharacterDetail{
    return this.character.let{
        CharacterDetail(
            externalId = it.id,
            name = it.name,
            status = it.status,
            gender = it.gender,
            species = it.species,
            hair = it.hair,
            origin = it.origin,
            //TODO: map abilities
            abilities = it.abilities.map { ability -> ability.name },
            img_url = it.img_url
        )
    }
}