package com.example.codingchallenge.ui

import android.util.Log
import com.example.codingchallenge.api.CharacterEndPoint
import com.example.codingchallenge.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Characters repository for getting the response from network call
 */
open class CharacterRepository(private var charEndPoint: CharacterEndPoint) {

    /**
     * suspendable function returns list of characters
     */
    suspend fun getCharactersFromApi(): List<Character>? {
        val response = charEndPoint.getApiResponse()
        return if(response.isSuccessful) {
            response.body()?.results
        } else {
            null
        }
    }
}