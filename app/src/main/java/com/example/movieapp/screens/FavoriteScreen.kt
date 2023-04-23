package com.example.movieapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.components.MovieCard

import com.example.movieapp.components.SimpleAppBar
import com.example.movieapp.models.Movie
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavHostController,
) {
    val coroutineScope = rememberCoroutineScope()

    val favoritesState by favoritesViewModel.getAllFavorites().collectAsState(initial = emptyList())

    Column {
        SimpleAppBar(title = "My favorite Movies", navController = navController)
        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.h4.fontSize,
            text = "Favorites"
        )
        Spacer(modifier = Modifier.size(5.dp))
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)

        for(movie: Movie in favoritesState) {
            MovieCard(movie,
                onFavoriteClick = {
                    coroutineScope.launch {
                        favoritesViewModel.updateFavorites(movie)
                    }
                },
                onDeleteClick = {
                    coroutineScope.launch {
                        movieViewModel.deleteMovie(movie)
                    }
                },
                onItemClick = { movieId ->
                    navController.navigate("${Screen.DetailScreen.route}/$movieId")
                }
            )
        }
    }
}



