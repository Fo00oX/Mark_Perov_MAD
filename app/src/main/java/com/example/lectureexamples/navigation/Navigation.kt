package com.example.lectureexamples.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lectureexamples.screens.DetailScreen
import com.example.lectureexamples.screens.HomeScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController)
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }
    }
}

@Composable
fun TopBar() {
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
                DropdownMenuItem(onClick = { /* Handle favorites click */ }) {
                    Text("Favorites")
                }
            }
        }
    )
}