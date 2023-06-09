package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.movieapp.navigation.ScreenRoute
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.components.SimpleAppBar
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel
import kotlinx.coroutines.launch

/**

A composable function that displays the user's favorite movies list.

 [movieViewModel] The view model for handling movies data.

 [favoritesViewModel] The view model for handling favorite movies data.

 [navController] The navigation controller used to navigate between screens.
 */
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

        LazyColumn {
            items(favoritesState) { movie ->
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
                        navController.navigate("${ScreenRoute.DetailScreenRoute.route}/$movieId")
                    }
                )
            }
        }
    }
}

