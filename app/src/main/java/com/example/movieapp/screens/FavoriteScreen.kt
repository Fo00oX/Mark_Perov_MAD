package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.components.MovieCard
import com.example.movieapp.components.MovieViewModel
import com.example.movieapp.components.SimpleAppBar
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.Route

@Composable
fun FavoriteScreen(
    movieViewModel: MovieViewModel,
    navController: NavHostController,
) {
    val favoriteMovies = remember { mutableStateOf(movieViewModel.favoritesList) }

    LaunchedEffect(movieViewModel.favoritesList) {
        favoriteMovies.value = movieViewModel.favoritesList
    }

    Column {
        SimpleAppBar(title = "My favorite Movies", navController = navController)
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)

        LazyColumn {
            items(favoriteMovies.value) { movie ->
                MovieCard(movie, onFavoriteClick = {
                    movieViewModel.updateFavorites(movie)
                    navController.popBackStack()
                    navController.navigate(Route.Favorites.route)
                }) {
                    navController.navigate("${Route.Detail.route}/${movie.id}")
                }
            }
        }
    }
}
