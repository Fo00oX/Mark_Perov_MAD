package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.Genre
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.repositories.MovieRepository
import kotlinx.coroutines.launch

class AddMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch {
        }
    }

    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )

    fun isValidMovie(title: String, year: String, genres: String, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank()
                && year.isNotBlank()
                && genres.isNotBlank()
                && director.isNotBlank()
                && actors.isNotBlank()
                && rating > 0.0f
                )
    }

    suspend fun addMovie(movie: Movie) { //can be blocking, only from a coroutine callable
        repository.add(movie)
    }
}