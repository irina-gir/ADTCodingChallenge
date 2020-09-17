package com.example.codingchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codingchallenge.models.Character
import com.example.codingchallenge.models.Location
import com.example.codingchallenge.models.Origin
import com.example.codingchallenge.ui.CharacterRepository
import com.example.codingchallenge.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var repo: CharacterRepository
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        Dispatchers.setMain(coroutineDispatcher)
        repo = Mockito.mock(CharacterRepository::class.java)
        mainViewModel = MainViewModel(repo)
    }

    @Test
    fun testRepo(){
        runBlocking {
            Mockito.`when`(repo.getCharactersFromApi()).thenReturn(getDummyData())
            mainViewModel.getCharacters()
            val list = mainViewModel.repoLiveData.value
            assert(list?.get(0)!!.created == "One")
            assert(list[1].gender == "One")
            assert(list[2].name == "One")
            assert(list[3].image == "Image")
        }
    }

    private fun getDummyData(): List<Character>{
        val list = ArrayList<Character>()
        list.add(
            Character(
            created = "One",
                episode = listOf("1"),
                gender = "One",
                name = "One",
                image = "Image",
                id = 1,
                origin = Origin("One", "some"),
                location = Location("One", "some"),
                species = "One",
            status = "One",
            type = "One",
            url = "One")
        )
        return list
    }

    @After
    fun cleanUp(){
        Dispatchers.resetMain()
    }
}