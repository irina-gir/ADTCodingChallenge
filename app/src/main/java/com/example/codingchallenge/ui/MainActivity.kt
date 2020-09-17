package com.example.codingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codingchallenge.R
import com.example.codingchallenge.api.RetrofitService
import com.example.codingchallenge.models.Character
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var charAdapter = CharacterAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,
        MainViewModelFactory(CharacterRepository(RetrofitService.getCharEndPoint())))
            .get(MainViewModel::class.java)

        mainViewModel.getCharacters()
        init()
        observer()
    }

    /**
     * observe LiveData from viewModel, and pass the data to the adapter
     */
    private fun observer() {
        mainViewModel.repoLiveData.observe(this, Observer {
            if(it.isNotEmpty()){
                charAdapter.setData(it as ArrayList<Character>)
            }
        })
    }

    /**
     * initialize th recycler view
     */
    private fun init() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = charAdapter
    }
}