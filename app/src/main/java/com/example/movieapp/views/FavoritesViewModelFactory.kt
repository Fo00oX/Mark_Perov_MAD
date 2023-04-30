package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.repositories.MovieRepository

/**

A factory class that provides the [FavoritesViewModel].

This factory is used to create instances of [FavoritesViewModel] with the specified [repository].

It implements the [ViewModelProvider.Factory] interface to provide the [create] method,

which is used to create the [FavoritesViewModel] instance.

@property repository the [MovieRepository] instance that the [FavoritesViewModel] needs to be created.
 */
class FavoritesViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    /**

    Creates an instance of the [FavoritesViewModel] class.
    @throws IllegalArgumentException if the [modelClass] is not assignable from [FavoritesViewModel].
    @return an instance of the [FavoritesViewModel] class.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}