package com.example.movieapp.utils

import android.content.Context
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.data.repositories.MovieRepository
import com.example.movieapp.views.AddMovieViewModelFactory
import com.example.movieapp.views.DetailsViewModelFactory
import com.example.movieapp.views.FavoritesViewModelFactory
import com.example.movieapp.views.MovieViewModelFactory

/**

A utility object that provides various ViewModel factories for the Movie app.
 */
object InjectorUtils {

    /**

    Returns a [MovieRepository] instance based on the [context].
    @param context The context used to get the [MovieDatabase] instance.
    @return The [MovieRepository] instance.
     */
    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MovieDatabase.getDatabase(context).MovieDao())
    }

    /**

    Provides a [MovieViewModelFactory] instance for creating [MovieViewModel].
    @param context The context used to get the [MovieRepository] instance.
    @return The [MovieViewModelFactory] instance.
     */
    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
        return MovieViewModelFactory(getMovieRepository(context))
    }

    fun provideFavoritesViewModelFactory(context: Context): FavoritesViewModelFactory {
        return FavoritesViewModelFactory(getMovieRepository(context))
    }

    fun provideDetailsViewModelFactory(context: Context): DetailsViewModelFactory {
        return DetailsViewModelFactory(getMovieRepository(context))
    }

    fun provideAddMovieViewModelFactory(context: Context): AddMovieViewModelFactory {
        return AddMovieViewModelFactory(getMovieRepository(context))
    }
}