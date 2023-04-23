package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.movieapp.navigation.SetupNavGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.utils.InjectorUtils
import com.example.movieapp.views.AddMovieViewModel
import com.example.movieapp.views.DetailsViewModel
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel

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
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()

                    movieViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(LocalContext.current))
                    favoritesViewModel = viewModel(factory = InjectorUtils.provideFavoritesViewModelFactory(LocalContext.current))
                    detailsViewModel = viewModel(factory = InjectorUtils.provideDetailsViewModelFactory(LocalContext.current))
                    addMovieViewModel = viewModel(factory = InjectorUtils.provideAddMovieViewModelFactory(LocalContext.current))

                    Column {
                        SetupNavGraph(movieViewModel, favoritesViewModel, detailsViewModel, addMovieViewModel, navController)
                    }
                }
            }
        }
    }
}

