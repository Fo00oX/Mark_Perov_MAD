package com.example.movieapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.components.MovieCard
import com.example.movieapp.components.MovieViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.Route

val defaultMovie = Movie(
    id = "tt0499549",
    title = "Avatar",
    year = "2009",
    genre = "Action, Adventure, Fantasy",
    director = "James Cameron",
    actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
    plot = "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
    rating = "7.9")

@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Column{
        HomeScreenBar("Movies", navController)
        MovieList(movieViewModel, navController)
    }
}


@Composable
fun HomeScreenBar(title: String = "Movies", navController: NavHostController) {
    var addButtonClickedState by remember {
        mutableStateOf(false)
    }
    var optionsState by remember {
        mutableStateOf(false)
    }
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
                    navController.navigate(Route.AddMovie.route)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Movie")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Add Movie")
                }
                DropdownMenuItem(onClick = {
                    navController.navigate(Route.Favorites.route)
                }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Favorites")
                }
            }
        }
    }
}


@Composable
fun MovieList(
    movieViewModel: MovieViewModel,
    navController: NavHostController,
) {
    LazyColumn {
        items(movieViewModel.movies) { movie ->
            MovieCard(movie, onFavoriteClick = { movieViewModel.updateFavorites(movie) }){
                navController.navigate("${Route.Detail.route}/${movie.id}")
            }
        }
    }
}




