package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.components.MovieViewModel
import com.example.movieapp.screens.AddMovieScreen
import com.example.movieapp.screens.DetailScreen
import com.example.movieapp.screens.FavoriteScreen
import com.example.movieapp.screens.HomeScreen

sealed class Route(val route: String) {
    object Home : Route("home")
    object Favorites : Route("favorites")
    object Detail : Route("detail/{movieId}")
    object AddMovie : Route( "add_movie_screen")
}

@Composable
fun Navigation(
    movieViewModel: MovieViewModel,
    navController: NavHostController,
    ) {
        NavHost(navController = navController, Route.Home.route) {
            composable(Route.Home.route) {
                HomeScreen(movieViewModel, navController)
            }
            composable(
                "${Route.Detail.route}/{movieId}",
                arguments = listOf(navArgument("movieId") {
                    type = NavType.StringType
                })
            ) { backStackEntry ->
                DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"), movieViewModel, navController)
            }
            composable(Route.Favorites.route) {
                FavoriteScreen(movieViewModel, navController)
            }
            composable(Route.AddMovie.route) {
                AddMovieScreen(Modifier, movieViewModel, navController)
            }
        }
    }