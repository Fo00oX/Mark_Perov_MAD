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
    Column {
        SimpleAppBar(title = "My favorite Movies", navController = navController)
        Text(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            fontSize = MaterialTheme.typography.h4.fontSize,
            text = "Favorites"
        )
        Spacer(modifier = Modifier.size(5.dp))
        Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)

        LazyColumn {
            items(movieViewModel.favoritesList) { movie ->
                MovieCard(movie, onFavoriteClick = { movieViewModel.updateFavorites(movie) }) {
                    navController.navigate("${Route.Detail.route}/${movie.id}")
                }
            }
        }
    }
}
