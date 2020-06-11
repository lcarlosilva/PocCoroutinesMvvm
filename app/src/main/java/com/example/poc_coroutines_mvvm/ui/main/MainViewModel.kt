package com.example.poc_coroutines_mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val filmesLiveData = MutableLiveData<List<Movies>>()

    fun getMovies() {
        repository.getFilmes {filmes ->
            this.filmesLiveData.postValue(filmes)
        }
    }

    fun getMoviesCoroutines() {
        CoroutineScope(Dispatchers.Main).launch {
            val filmes = withContext(Dispatchers.Default) {
                repository.getMoviesCoroutines()
            }
            filmesLiveData.value = filmes
        }
    }

    class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}
