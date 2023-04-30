package com.example.movieapp.data.repositories

import com.example.movieapp.data.MovieDao
import com.example.movieapp.data.models.Movie

/**

A repository class that handles data operations for the Movie entity.

It provides an abstraction over the underlying data access layer, which is a [MovieDao].

[movieDao] The Data Access Object (DAO) for the Movie entity.
 */
class MovieRepository(private val movieDao: MovieDao) {

    suspend fun add(movie: Movie) = movieDao.add(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

    suspend fun update(movie: Movie) = movieDao.update(movie)

    fun getAllMovies() = movieDao.getAllMovies()

    suspend fun deleteAll() = movieDao.deleteAll()

    fun getAllFavorites() = movieDao.getAllFavorites()

    fun getMovieById(movieId: Int) = movieDao.getMovieById(movieId)

}
