# Mobile App Development (MAD) - Movie App

## This repository contains the source code for a simple movie app developed using Jetpack Compose in Android Studio as part of the Mobile App Development (MAD) course. The app allows users to browse a list of movies, view movie details, and mark movies as favorites.
Features

    Browse a list of movies
    View movie details, including images, plot, director, and actors
    Mark movies as favorites
    View a list of favorite movies

### Project Structure

    MainActivity.kt - The main activity that sets up the navigation and theme
    MovieModel.kt - Contains the Movie data class and a function to retrieve a list of movies
    MovieViewModel.kt - A ViewModel to handle the shared state of favorite movies
    Navigation.kt - Contains the navigation setup using Jetpack Navigation
    Screens.kt - Contains the Composable functions for the different screens: HomeScreen, FavoriteScreen, and DetailScreen
    MovieCard.kt - A Composable function that displays the movie card with movie details
    MovieRow.kt - A Composable function that displays a movie row in the movie list
    ImageList.kt - A Composable function to display a list of movie images


### Dependencies

    Jetpack Compose
    Jetpack Navigation
    ViewModel and LiveData
    Accompanist (for image loading)
