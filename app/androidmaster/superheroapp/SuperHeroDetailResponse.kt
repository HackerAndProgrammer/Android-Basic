package com.example.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

// These data classes will be used to get the name, biography, power-stats, appearance, speed, etc. Basically, all the detail we need so we can show it to the user
data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperheroImageDetailResponse,
    @SerializedName("biography") val biography: Biography
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class SuperheroImageDetailResponse(@SerializedName("url") val url: String)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)