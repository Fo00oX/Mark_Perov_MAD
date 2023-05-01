package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.ui.screens.DetailScreen
import com.example.movieapp.ui.screens.FavoriteScreen
import com.example.movieapp.ui.screens.HomeScreen
import com.example.movieapp.ui.screens.AddMovieScreen
import com.example.movieapp.views.AddMovieViewModel
import com.example.movieapp.views.DetailsViewModel
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel

/**

A composable function that sets up the navigation for the app using Jetpack Navigation. It defines the navigation
routes for different screens and manages the navigation between these screens.

 [movieViewModel] The [MovieViewModel] instance to manage the movie screen data and interactions.

 [favoritesViewModel] The [FavoritesViewModel] instance to manage the favorites screen data and interactions.

 [detailsViewModel] The [DetailsViewModel] instance to manage the details screen data and interactions.

 [addMovieViewModel] The [AddMovieViewModel] instance to manage the add movie screen data and interactions.

 [navController] The [NavHostController] instance to handle the navigation between different screens.
 */
@Composable
fun SetupNavigation(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    detailsViewModel: DetailsViewModel,
    addMovieViewModel: AddMovieViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, ScreenRoute.HomeScreenRoute.route) {
        composable(ScreenRoute.HomeScreenRoute.route) {
            HomeScreen(movieViewModel, favoritesViewModel, navController)
        }
         //
         //Inside the lambda, it first attempts to get the movieId argument from the backStackEntry's arguments.
         //If the movieId is successfully retrieved (i.e., not null),
         //it calls the DetailScreen composable function with parameters
         //
        composable(
            "${ScreenRoute.DetailScreenRoute.route}/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("movieId")
                ?.let { DetailScreen(
                    movie = detailsViewModel.getMovieById(movieId = it),
                    movieViewModel = movieViewModel,
                    favoritesViewModel = favoritesViewModel,
                    navController = navController)
                }
        }

        composable(ScreenRoute.FavoriteScreenRoute.route) {
            FavoriteScreen(movieViewModel, favoritesViewModel, navController)
        }

        composable(ScreenRoute.AddMovieScreenRoute.route) {
            AddMovieScreen(Modifier, addMovieViewModel, navController)
        }
    }
}