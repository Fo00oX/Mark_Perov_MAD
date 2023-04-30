package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.Genre
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.repositories.MovieRepository
import kotlinx.coroutines.launch

/**

ViewModel for AddMovieScreen. Handles adding new movies to the database

repository The MovieRepository instance that handles data operations on the database
 */
class AddMovieViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch {
        }
    }

    /**

    Represents a selectable list item for the genre field in the AddMovieScreen.
     */
    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )

    /**
    Determines if a movie is valid.
     */
    fun isValidMovie(title: String,
                     year: String,
                     genres: String,
                     director: String,
                     actors: String,
                     rating: Float): Boolean {
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