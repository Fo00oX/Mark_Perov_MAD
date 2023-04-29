package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _favorites = MutableStateFlow(listOf<Movie>())
    val favorites: StateFlow<List<Movie>> = _favorites.asStateFlow()

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