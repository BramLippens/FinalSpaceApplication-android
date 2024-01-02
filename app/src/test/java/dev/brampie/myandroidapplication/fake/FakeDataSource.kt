package dev.brampie.myandroidapplication.fake

import dev.brampie.myandroidapplication.model.character.Character
import dev.brampie.myandroidapplication.model.location.Location

object FakeDataSource {
    val characters = listOf(
        Character(
            internalId = 1,
            externalId = 101,
            name = "Gary Goodspeed",
            status = "Alive",
            species = "Human",
            gender = "Male",
            hair = "Brown",
            origin = "Earth",
            abilities = listOf("Charm", "Piloting"),
            img_url = "https://finalspaceapi.com/api/character/avatar/gary_goodspeed.png"
        ),
        Character(
            internalId = 2,
            externalId = 102,
            name = "Mooncake",
            status = "Alive",
            species = "Planet Destroyer",
            gender = "N/A",
            hair = "N/A",
            origin = "Unknown",
            abilities = listOf("Destruction", "Flight"),
            img_url = "https://finalspaceapi.com/api/character/avatar/mooncake.jpg"
        ),
        Character(
            internalId = 3,
            externalId = 103,
            name = "Quinn Ergon",
            status = "Alive",
            species = "Human",
            gender = "Female",
            hair = "Blonde",
            origin = "Earth",
            abilities = listOf("Leadership", "Tactics"),
            img_url = "https://finalspaceapi.com/api/character/avatar/quinn_ergon.jpg"
        )
    )

    val locations = listOf(
        Location(
            internalId = 1,
            externalId = 201,
            name = "Earth",
            type = "Planet",
            img_url = "https://finalspaceapi.com/api/location/image/earth.jpg"
        ),
        Location(
            internalId = 2,
            externalId = 202,
            name = "Final Space",
            type = "Dimension",
            img_url = "https://finalspaceapi.com/api/location/image/final_space.jpg"
        ),
        Location(
            internalId = 3,
            externalId = 203,
            name = "Tera Con Prime",
            type = "Destroyed planet",
            img_url = "https://finalspaceapi.com/api/location/image/tera_con_prime.jpg"
        )
    )
}