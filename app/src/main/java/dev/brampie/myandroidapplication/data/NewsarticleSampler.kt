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
            list.add(Newsarticle("Google",item, if (Random.nextInt(0, 2) == 0) { "lorem ipsum dolor sit" } else "consectetur adipiscing elit"))
        }
        list
    }
}