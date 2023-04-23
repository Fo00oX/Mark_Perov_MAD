package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.example.movieapp.screens.*
import com.example.movieapp.views.AddMovieViewModel
import com.example.movieapp.views.DetailsViewModel
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel

@Composable
fun SetupNavGraph(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    detailsViewModel: DetailsViewModel,
    addMovieViewModel: AddMovieViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(movieViewModel, favoritesViewModel, navController)
        }

        composable(
            "${Screen.DetailScreen.route}/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("movieId")
                ?.let { DetailScreen(movie = detailsViewModel.getMovieById(movieId = it), movieViewModel = movieViewModel, favoritesViewModel = favoritesViewModel, navController = navController) }
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(movieViewModel, favoritesViewModel, navController)
        }

        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(Modifier, addMovieViewModel, navController)
        }
    }
}