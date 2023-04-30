package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.models.Movie
import com.example.movieapp.navigation.ScreenRoute
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.views.FavoritesViewModel
import com.example.movieapp.views.MovieViewModel
import kotlinx.coroutines.launch

/**

[defaultMovie] is a default value for the Movie class, with predefined values for its attributes.
 */
val defaultMovie = Movie(
    title = "Avatar",
    year = "2009",
    genre = "Action, Adventure, Fantasy",
    director = "James Cameron",
    actors = "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang",
    plot = "A paraplegic marine dispatched to the moon Pandora on a unique " +
            "mission becomes torn between following his orders and protecting " +
            "the world he feels is his home.",
    images = listOf("https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyOTYyMzUxNl5BMl5BanBnXkFtZTcwNTg0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg",
        "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTY2ODQ3NjMyMl5BMl5BanBnXkFtZTcwODg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxOTEwNDcxN15BMl5BanBnXkFtZTcwOTg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
        "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYxMDg1Nzk1MV5BMl5BanBnXkFtZTcwMDk0MTUzNA@@._V1_SX1500_CR0,0,1500,999_AL_.jpg"),
    rating = 7.9f)

/**

[HomeScreen] is a composable function that displays a list of movies and a top bar with options.
 [movieViewModel] an instance of MovieViewModel.
 [favoritesViewModel] an instance of FavoritesViewModel.
 [navController] a NavHostController instance used for navigation between different screens.
 */
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

/**

[HomeScreenAppBar] is a composable function that displays the top bar for the [HomeScreen].

 [title] the title of the top bar.

 [movieViewModel] an instance of MovieViewModel.

 [navController] a NavHostController instance used for navigation between different screens.
 */
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
        .background(Color.Black)
        .fillMaxWidth()
        .padding(11.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Text(title, style = MaterialTheme.typography.h6, color = Color.White)
        Column {
            Icon(imageVector = Icons.Default.MoreVert,
                contentDescription = "Settings",
                tint = Color.White,
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
                    navController.navigate(ScreenRoute.AddMovieScreenRoute.route)
                }) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Add Movie")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Add Movie")
                }
                DropdownMenuItem(onClick = {
                    navController.navigate(ScreenRoute.FavoriteScreenRoute.route)
                }) {
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Favorites")
                }
                DropdownMenuItem(onClick = {
                    coroutineScope.launch {
                        movieViewModel.deleteAllMovies()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = "Delete")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Clear Movies")
                }
            }
        }
    }
}

/**

A Composable function that displays a list of movies in a lazy column.

 [movieViewModel] The ViewModel used to interact with the movies data.

 [favoritesViewModel] The ViewModel used to interact with the favorites data.

 [navController] The navigation controller used to navigate to the details screen.
 */
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
                    navController.navigate("${ScreenRoute.DetailScreenRoute.route}/$movieId")
                }
            )
        }
    }
}
