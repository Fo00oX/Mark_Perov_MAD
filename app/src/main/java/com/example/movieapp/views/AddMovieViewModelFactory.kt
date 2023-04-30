package com.example.movieapp.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.repositories.MovieRepository

/**

A factory class that creates instances of [AddMovieViewModel] with the provided [repository].
Implements the [ViewModelProvider.Factory] interface, which allows instances of this factory
to be used to create and configure [ViewModel] objects within a [ViewModelProvider].
@param repository The [MovieRepository] to be used by the created [AddMovieViewModel] instances.
*/
class AddMovieViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMovieViewModel::class.java)) {
            return AddMovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}