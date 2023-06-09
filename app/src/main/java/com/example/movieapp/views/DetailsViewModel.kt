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

The [DetailsViewModel] class is responsible for managing the data related to a specific [movie]'s details.

It holds the state of the selected movie and provides a function to retrieve the movie from the repository.

@param [repository] is used to get the movie details
 */
class DetailsViewModel(private val repository: MovieRepository) : ViewModel() {

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