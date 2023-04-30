package com.example.movieapp.navigation

/**

Sealed class representing the different screens that can be navigated to in the Movie App.
The class has four subtypes, which are [HomeScreenRoute], [DetailScreenRoute],
[FavoriteScreenRoute], and [AddMovieScreenRoute].
Each subtype represents a specific screen and is defined as an object within the ScreenRoute class.
The route property of each subtype specifies the unique identifier for the screen.
By using a sealed class, the set of possible screen routes is limited to only those defined within the class.
This helps ensure that all possible routes are handled and prevents unintended routes from being added or accessed.
Additionally, sealed classes are useful for pattern matching and exhaustive when statements,
as the compiler can ensure that all possible subtypes are covered.
 */
sealed class ScreenRoute(val route: String) {
    object HomeScreenRoute: ScreenRoute(route = "home_screen")
    object DetailScreenRoute: ScreenRoute(route = "detail_screen")
    object FavoriteScreenRoute: ScreenRoute(route = "favorite_screen")
    object AddMovieScreenRoute: ScreenRoute(route = "add_movie_screen")
}
