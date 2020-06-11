package com.example.poc_coroutines_mvvm.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepository {

    fun getFilmes(callback: (filmes: List<Movies>) -> Unit) {
        Thread(Runnable {
            Thread.sleep(3000)
            callback.invoke(
                buildListMovies()
            )
        }).start()
    }

    suspend fun getMoviesCoroutines(): List<Movies> {
        return withContext(Dispatchers.Default){
            delay(3000)
            buildListMovies()
        }
    }

    private fun buildListMovies(): List<Movies> {
        return listOf(
            Movies(1, "Titulo filme A", "2,30"),
            Movies(2, "Titulo filme B", "1,80")
        )
    }

}