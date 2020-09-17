package com.example.codingchallenge.api

import com.example.codingchallenge.models.CharacterApiModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * provides retrofit builder
 */
object RetrofitService {
    private const val BASE_URL = "https://rickandmortyapi.com/"

    val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCharEndPoint():CharacterEndPoint = client.create(CharacterEndPoint::class.java)
}

/**
 * provides character endpoint
 */
interface CharacterEndPoint{
    @GET("api/character/")
    suspend fun getApiResponse(): Response<CharacterApiModel>
}