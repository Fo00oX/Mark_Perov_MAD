package com.example.movieapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.components.MovieCard

import com.example.movieapp.models.Movie
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel
import kotlinx.coroutines.launch

val defaultMovie = Movie(title = "Avatar", year = "2009", genre = "Action, Adventure, Fantasy", director = "James Cameron", actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang", plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"), rating = 7.9f)


@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavHostController = rememberNavController(),
) {
    Column{
        HomeScreenAppBar("Movies", movieViewModel, navController)
        MovieList(movieViewModel, favoritesViewModel, navController)
    }
}

@Composable
fun HomeScreenAppBar(
    title: String = "Movies",
    movieViewModel: MovieViewModel,
    navController: NavHostController
) {
    var optionsState by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()

    Row(modifier = Modifier
        .background(Color.Blue)
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(title, style = MaterialTheme.typography.h6, color = Color.White)
        Column {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Settings", tint = Color.White,
                modifier = Modifier.clickable(onClick = {
                    optionsState = !optionsState
                }),
            )
            DropdownMenu(
                expanded = optionsState,
                onDismissRequest = {
                    optionsState = false
                },
            ) {
                DropdownMenuItem(onClick = {
                    navController.navigate(Screen.AddMovieScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Movie")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Add Movie")
                }
                DropdownMenuItem(onClick = {
                    navController.navigate(Screen.FavoriteScreen.route)
                }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Favorites")
                }
                DropdownMenuItem(onClick = {
                    coroutineScope.launch {
                        movieViewModel.deleteAllMovies()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Clear Movies")
                }
            }
        }
    }
}

@Composable
fun MovieList(
    movieViewModel: MovieViewModel,
    favoritesViewModel: FavoritesViewModel,
    navController: NavHostController,
) {
    val coroutineScope = rememberCoroutineScope()

    val movies by movieViewModel.movies.collectAsState()
    val moviesState = rememberUpdatedState(movies)

    LazyColumn {
        items(moviesState.value) { movie ->
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
