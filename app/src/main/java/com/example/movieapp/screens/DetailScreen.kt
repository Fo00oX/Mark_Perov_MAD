package com.example.movieapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.components.MovieCard
import com.example.movieapp.components.SimpleAppBar
import com.example.movieapp.models.Movie
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    movie: Movie,
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavHostController,
) {
    movie.let {
        val coroutineScope = rememberCoroutineScope()
        Column {
            SimpleAppBar(movie.title, navController)
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
                }
            )
            Spacer(modifier = Modifier.size(5.dp))
            Divider(startIndent = 5.dp, thickness = 0.5.dp, color = Color.DarkGray)
            Text(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                fontSize = MaterialTheme.typography.h4.fontSize,
                text = "Movie Images"
            )
            ImagesList(movie)
        }
    }
}

@Composable
fun DrawImage(image: String = defaultMovie.images[0]) {
    Box {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = image).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
        )
        val painterState = painter.state
        if (painterState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
        Image(
            painter = painter,
            contentDescription = "Movie Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(280.dp)
                .padding(4.dp),
        )
    }
}

@Composable
fun ImagesList(movie: Movie = defaultMovie) {
    LazyRow {
        items(movie.images.subList(1, movie.images.size)) {image ->
            DrawImage(image)
        }
    }
}
