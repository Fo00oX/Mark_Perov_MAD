package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: MovieRepository, movieId: Int = 0) : ViewModel() {

    private var _movie = MutableStateFlow(Movie())
    val movie: StateFlow<Movie> = _movie.asStateFlow()

    fun getMovieById(movieId: Int): Movie {
        viewModelScope.launch {
            repository.getMovieById(movieId).collect{ foundMovie ->
                _movie.value = foundMovie
            }
        }
        return movie.value
    }
}