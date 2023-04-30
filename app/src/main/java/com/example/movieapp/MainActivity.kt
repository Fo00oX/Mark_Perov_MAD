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

/**

The MainActivity is the main entry point of the application.
It sets up and initializes the view models and navigation controller,

and sets up the navigation routes.
 */
class MainActivity : ComponentActivity() {
    // Variables to store instances of required view models and navigation controller
    lateinit var navController: NavHostController
    lateinit var movieViewModel: MovieViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var detailsViewModel: DetailsViewModel
    lateinit var addMovieViewModel: AddMovieViewModel

    /**

    This function is called when the activity is created.

    It sets the content view, initializes the view models and navigation, and sets up the [navigation] routes.

     savedInstanceState A bundle containing the activity's previously saved state, or null if there is no saved state.
     */
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
        //reseedDatabase()
    }
    /**

    Reseed the database for debugging/testing purposes.
     */
    private fun reseedDatabase() {
        val movieDatabase = MovieDatabase.getDatabase(this)
        val movieDatabaseCallback = MovieDatabaseCallback(this)

        CoroutineScope(Dispatchers.IO).launch {
            movieDatabaseCallback.seedDatabase(movieDatabase)
        }
    }
}

