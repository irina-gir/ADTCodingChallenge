package com.example.codingchallenge.ui

import com.example.codingchallenge.api.CharacterEndPoint
import com.example.codingchallenge.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Characters repository for getting the response from network call
 */
class CharacterRepository(private var charEndPoint: CharacterEndPoint) {

    /**
     * suspendable function returns list of characters
     */
    suspend fun getCharactersFromApi(): List<Character>?{
        return withContext(Dispatchers.IO){
            val response = charEndPoint.getApiResponse()
            response.results
        }
    }
}