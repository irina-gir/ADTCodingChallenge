package com.example.codingchallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codingchallenge.models.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * View Model with liveData to be observed
 */
open class MainViewModel(private val repository: CharacterRepository): ViewModel() {

    val repoLiveData =  MutableLiveData<List<Character>> ()
//            by lazy {
//        MutableLiveData<List<Character>>()
//    }

    /**
     * calling CharacterRepository response within coroutine scope
     */
    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch{
            val list = repository.getCharactersFromApi()
            repoLiveData.postValue(list)
        }
    }

}

/**
 * MainViewModel Factory
 */
class MainViewModelFactory(private val repository: CharacterRepository)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}