package com.example.movieapp.navigation

sealed class ScreenRoute(val route: String) {
    object HomeScreenRoute: ScreenRoute(route = "home_screen")
    object DetailScreenRoute: ScreenRoute(route = "detail_screen")
    object FavoriteScreenRoute: ScreenRoute(route = "favorite_screen")
    object AddMovieScreenRoute: ScreenRoute(route = "add_movie_screen")
}
