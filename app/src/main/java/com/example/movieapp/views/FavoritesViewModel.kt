package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/** FavoritesViewModel is responsible for handling favorite movie data and actions.
 * It fetches favorite movies from the repository and provides it as a state flow.
 *
 */
class FavoritesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _favorites = MutableStateFlow(listOf<Movie>())

    init {
        viewModelScope.launch {
            repository.getAllFavorites().collect{ favoriteList ->
                if(!favoriteList.isNullOrEmpty()) {
                    _favorites.value = favoriteList
                }
            }
        }
    }

    fun getAllFavorites(): Flow<List<Movie>> {
        return repository.getAllFavorites()
    }

    suspend fun updateFavorites(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        repository.update(movie)
    }

}