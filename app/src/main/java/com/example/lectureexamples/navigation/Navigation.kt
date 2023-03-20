package com.example.lectureexamples.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lectureexamples.models.getMovies
import com.example.lectureexamples.screens.DetailScreen
import com.example.lectureexamples.screens.FavoriteScreen
import com.example.lectureexamples.screens.HomeScreen

sealed class Route(val route: String) {
    object Home : Route("home")
    object Favorites : Route("favorites")
    object Detail : Route("detail/{movieId}")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Home.route) {
        composable(route = Route.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Route.Favorites.route) {
            FavoriteScreen(navController)
        }

        composable(
            route = Route.Detail.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            val selectedMovie = getMovies().firstOrNull { it.id == movieId }

            if (selectedMovie != null) {
                DetailScreen(navController, selectedMovie)
            } else {
                Text("Movie not found")
            }
        }
    }
}
