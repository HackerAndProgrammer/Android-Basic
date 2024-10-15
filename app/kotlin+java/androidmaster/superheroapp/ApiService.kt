package com.example.androidmaster.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// This interface will be used to define the API endpoints. Basically, we will define the method that we will use to access the API
interface ApiService {

    // Use the GET method to access the API endpoint (by name)
    @GET("api/35ec7077b2af6e96dc9b0de282028bc0/search/{name}")

    // ¿Why is "suspended"? Because this function will be called from a coroutine, and it will be suspended until the response is received
    suspend fun getSuperheroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

    // Use the GET method to access the API endpoint (by id)
    @GET("/api/35ec7077b2af6e96dc9b0de282028bc0/{id}")

     /*
     *This function will be used to get the superhero/villain id from the API. This time,
     *it will not be a Data Response object, but a SuperHeroDetailResponse object,
     *because in that class, we have all the powerstats seted,
     *so we can show them to the user
      */
    suspend fun getSuperheroDetail(@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>
}