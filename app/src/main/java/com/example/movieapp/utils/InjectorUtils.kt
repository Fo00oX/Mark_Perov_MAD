package com.example.movieapp.utils

import android.content.Context
import com.example.movieapp.components.MovieViewModelFactory
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.views.AddMovieViewModelFactory
import com.example.movieapp.views.DetailsViewModelFactory
import com.example.movieapp.views.FavoritesViewModelFactory

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MovieDatabase.getDatabase(context).MovieDao())
    }

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