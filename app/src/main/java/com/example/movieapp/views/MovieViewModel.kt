package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [MovieViewModel] is responsible for handling movie data and actions.It
 * fetches movies from the [repository] and provides it as a state flow.
 *
 */
class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    // MutableStateFlow to store the list of movies
    private val _movies = MutableStateFlow(listOf<Movie>())
    // StateFlow to expose the list of movies
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private fun fetchMovies() {
        viewModelScope.launch {
            repository.getAllMovies().collect { movieList ->
                if (!movieList.isNullOrEmpty()) {
                    _movies.value = movieList
                } else {
                    _movies.value = listOf()
                }
            }
        }
    }

    init {
        fetchMovies()
    }

    suspend fun deleteMovie(movie: Movie) {
        repository.delete(movie)
        fetchMovies()
    }

    suspend fun deleteAllMovies() {
        repository.deleteAll()
        fetchMovies()
    }
}