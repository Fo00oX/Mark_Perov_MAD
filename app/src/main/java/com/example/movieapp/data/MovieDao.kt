package com.example.movieapp.data

import androidx.room.*
import com.example.movieapp.data.models.Movie
import kotlinx.coroutines.flow.Flow

/**Android Room Persistence Library annotation, which tells Room to generate an implementation of
 the DAO at compile time. The MovieDao interface defines methods for various database operations
  such as [add], [update], [delete], and querying movies like: [getAllMovies], [getAllFavorites],
[getMovieById], [deleteAll].
*/
@Dao
interface MovieDao {
    // CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavorite = true")
    fun getAllFavorites(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun getMovieById(movieId: Int): Flow<Movie>

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}