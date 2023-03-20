package com.example.lectureexamples.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lectureexamples.components.MovieCard
import com.example.lectureexamples.models.Movie
import com.example.lectureexamples.models.getMovies

@Composable
fun HomeScreen(navController: NavController) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            HomeScreenBar(navController)
            MovieList(navController)
        }
    }
}

@Composable
fun HomeScreenBar(navController: NavController) {
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

@Preview
@Composable
fun MovieList(navController: NavController = rememberNavController(),
              movies: List<Movie> = getMovies()){
    LazyColumn{
        items(movies) {movie ->
            MovieRow(
                movie = movie,
            )  { movieId ->
                Log.d("MyList", "item clicked $movieId")
                // navigate to detailscreen
                navController.navigate("detail/$movieId")
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    remember { mutableStateOf(false) }

    MovieCard(movie, onItemClick)
}


