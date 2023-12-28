package dev.brampie.myandroidapplication.data

import dev.brampie.myandroidapplication.model.newsarticle.Newsarticle
import kotlin.random.Random

object NewsarticleSampler{
    val sampleNewsarticles = mutableListOf(
        "Museum closes new policies",
        "Market announces controversial bill",
        "New restaurant opens in town",
        "Mayor welcomes for renovations",
        "City protests fiscal challenges",
        "Economy booms record numbers",
        "Tourists welcomes controversial bill",
        "Scientists welcomes groundbreaking results",
        "Residents discovers rare artifacts"
    )

    val getAll: () -> MutableList<Newsarticle> = {
        val list = mutableListOf<Newsarticle>()
        for(item in sampleNewsarticles) {
            list.add(Newsarticle("Google",item, "https://picsum.photos/200/300?random=${Random.nextInt(0, 100)}", "2021-09-01"))
        }
        list
    }
}