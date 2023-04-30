package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.repositories.MovieRepository

/**

Factory for creating [MovieViewModel].
@param [movieRepository] The [repository] to be used for the ViewModel.
@throws IllegalArgumentException if the requested ViewModel class is unknown.
*/
class MovieViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}