package com.example.movieapp.screens

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home_screen")
    object DetailScreen: Screen(route = "detail_screen")
    object FavoriteScreen: Screen(route = "favorite_screen")
    object AddMovieScreen: Screen(route = "add_movie_screen")
}
