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

Composable function that sets up the navigation for the app using Jetpack Navigation.

 [movieViewModel] View model for the movie screen.

 [favoritesViewModel] View model for the favorites screen.

 [detailsViewModel] View model for the details screen.

 [addMovieViewModel] View model for the add movie screen.

 [navController] Navigation controller to handle the navigation.
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