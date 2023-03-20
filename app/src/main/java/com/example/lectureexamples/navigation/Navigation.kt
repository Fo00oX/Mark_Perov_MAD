package com.example.lectureexamples.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
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
            FavoriteScreen()
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

@Composable
fun TopBar(navController: NavController) {
    val showMenu = remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Movies") },
        actions = {
            IconButton(onClick = { showMenu.value = !showMenu.value }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Show menu")
            }
            DropdownMenu(
                expanded = showMenu.value,
                onDismissRequest = { showMenu.value = false }
            ) {
                DropdownMenuItem(onClick = { navController.navigate("favorites") }) {
                    Text("Favorites")
                }
            }
        }
    )
}

@Composable
fun DetailTopBar(navController: NavController, movieTitle: String) {
    TopAppBar(
        title = { Text(movieTitle) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}
