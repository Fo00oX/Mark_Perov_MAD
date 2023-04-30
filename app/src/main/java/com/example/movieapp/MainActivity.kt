package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.MovieDatabase
import com.example.movieapp.data.MovieDatabaseCallback
import com.example.movieapp.navigation.SetupNavigation
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.utils.InjectorUtils
import com.example.movieapp.views.AddMovieViewModel
import com.example.movieapp.views.DetailsViewModel
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    lateinit var movieViewModel: MovieViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var detailsViewModel: DetailsViewModel
    lateinit var addMovieViewModel: AddMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {

                Surface(
                    color = Color.Black
                ) {
                    navController = rememberNavController()

                    movieViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(LocalContext.current))
                    favoritesViewModel = viewModel(factory = InjectorUtils.provideFavoritesViewModelFactory(LocalContext.current))
                    detailsViewModel = viewModel(factory = InjectorUtils.provideDetailsViewModelFactory(LocalContext.current))
                    addMovieViewModel = viewModel(factory = InjectorUtils.provideAddMovieViewModelFactory(LocalContext.current))

                    Column {
                        SetupNavigation(movieViewModel, favoritesViewModel, detailsViewModel, addMovieViewModel, navController)
                    }
                }
            }
        }
        reseedDatabase()
    }
    private fun reseedDatabase() {
        val movieDatabase = MovieDatabase.getDatabase(this)
        val movieDatabaseCallback = MovieDatabaseCallback(this)

        CoroutineScope(Dispatchers.IO).launch {
            movieDatabaseCallback.seedDatabase(movieDatabase)
        }
    }
}

