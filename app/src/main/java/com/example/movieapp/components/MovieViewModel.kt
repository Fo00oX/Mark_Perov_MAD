package com.example.movieapp.components

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Genre
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

class MovieViewModel : ViewModel() {

    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )

    private val _movies = getMovies().toMutableStateList()
    val movies: MutableList<Movie>
        get() = _movies

    private val _favoritesList: MutableList<Movie> = mutableStateListOf()
    val favoritesList: List<Movie>
        get() = _favoritesList

    fun isValidMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank()
                && year.isNotBlank()
                && genres.isNotEmpty()
                && director.isNotBlank()
                && actors.isNotBlank()
                && rating > 0.0f)
    }

    fun addNewMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, images: List<String>, rating: Float) {
        if(isValidMovie(title, year, genres, director, actors, rating)) {
            val lastId = movies.last().id.substring(2).toIntOrNull() ?: 0
            movies.add(
                Movie(
                    id = "tt${lastId + 1}",
                    title = title,
                    year = year,
                    genre = genres.toString(),
                    director = director,
                    actors = actors,
                    plot = plot,
                    images = images,
                    rating = rating.toString()
                )
            )
        }
    }

    fun updateFavorites(movie: Movie) {
        movies.find { item ->
            item.id == movie.id
        }?.let {
            val favoriteState: Boolean = movie.isFavorite
            movie.isFavorite = !favoriteState

            if(!favoriteState) {
                _favoritesList.add(movie)
            }
            else{
                _favoritesList.remove(movie)
            }
        }
    }


    private fun resetFavoritesList() {
        _favoritesList.clear()
    }

    init {
        resetFavoritesList()
    }
}