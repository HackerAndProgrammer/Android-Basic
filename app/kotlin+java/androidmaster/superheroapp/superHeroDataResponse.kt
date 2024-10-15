package com.example.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

// This data class will be used to return the response of the user's search, and it will be used to parse the response
data class SuperHeroDataResponse(

    // Here, I am only accessing to two parameters: the response and the results. Inside the results, we have two parameters: the id, and the name.
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperHeroItemResponse>
)

// This data class will be used to return the id, and the name of the superhero
data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val superheroImage: SuperheroImageResponse
)

data class SuperheroImageResponse(
    @SerializedName("url") val url: String
)