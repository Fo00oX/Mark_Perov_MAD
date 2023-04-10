# Mobile App Development (MAD) - Movie App

This repository contains the source code for a simple movie app developed using Jetpack Compose in Android Studio as part of the Mobile App Development (MAD) course. The app allows users to browse a list of movies, view movie details, and mark movies as favorites.
Features

    Browse a list of movies
    View movie details, including images, plot, director, and actors
    Mark movies as favorites
    View a list of favorite movies

### Project Structure

    MainActivity - The main activity that sets up the navigation and theme
    MovieModel - Contains the Movie data class and a function to retrieve a list of movies
    MovieViewModel - A ViewModel to handle the shared state of favorite movies
    Navigation - Contains the navigation setup using Jetpack Navigation
    Screens - Contains the Composable functions for the different screens: HomeScreen, FavoriteScreen, and DetailScreen
    MovieCard - A Composable function that displays the movie card with movie details
    MovieRow - A Composable function that displays a movie row in the movie list
    ImageList - A Composable function to display a list of movie images
    
### Learning Diary´s

    The Project is structured in Learning Diary´s(LD)
    Each LD has an own Branch
    
### LD_1 - Number Guess (Deleted already)
    
Generate a random 4-digit number. The number must not contain repeating digits. Ask the user to guess the 4-digit number. The output should be in the format "n:m", where "n" is the number of digits guessed correctly regardless of their position, and "m" is the number of digits guessed correctly at their correct position. Here are some examples:

    Generated number: 8576
    User input: 1234, Output: 0:0
    User input: 5678, Output: 4:1
    User input: 5555, Output: 1:1
    User input: 3586, Output: 3:2
    
### LD_2 - Movie App
    
1.	Describe your top 3 learnings from implementing the exercises with the MovieApp in lecture. 

2.	Extend your app with a TopAppBar that contains a DropdownMenu and a single DropdownMenuItem (Favorites). Use a remember state variable to toggle the DropDownMenu when the IconButton from the AppBar is clicked.

3.	Make movie details (director, release year, plot, …) expandable. When the arrow icon is clicked show/hide movie details. Furthermore, the icon should toggle between up and down.

4.	Use Coil library to load images from Movie.kt URLs (images). Adapt the MovieRow to show an image from each movie

### LD_3 - Movie App

1.	Describe your top 3 learnings from implementing the exercises with the MovieApp in lecture

2.	DetailScreen 

    a)	Add a TopAppBar to your DetailScreen. It should show the selected movie’s title and an ArrowBack icon. When the ArrowBack icon is clicked, the app should navigate to the previous screen.

    b)	(Re)Use your MovieRow composable inside DetailScreen, to show selected movie information

    c)	Extend the DetailScreen with a LazyRow that shows all movie images inside scrollable Card composables

3.	FavoriteScreen 
Create a FavoriteScreen composable and add it to you Navigation. The app should navigate a user to FavoritesScreen if the DropDownMenuItem Favorites in the HomeScreen is clicked. It has a TopAppBar like DetailScreen before. FavoriteScreen renders a list of movies with MovieRow. The list of movies can be hardcoded for now

4.	App Refactoring
    a)	Refactor your composables to be small and concise – split your code into different files an packages depending on their responsibilitites (eg. Screesn, widgets, models and so on)
    b)	Use a sealed class instead of simple strings in your navigation
    c)	Write a composable SimpleAppBar that can be reused in DetailScreen and FavoriteScreen    

### LD_4 - Movie App

1.	Users can mark Movies as favorites (Favorite Icon). The icon can be toggled on each screen that uses the MovieRow composable. The state of each movie is consistent on all necessary screens (Home, Favorite, Detail). Use a ViewModel to handle the shared state. 
Note: Handling State in Lists of Objects is not trivial. To observe changes in object attributes you must declare the attribute as a StateHolder. See https://developer.android.com/codelabs/jetpack-compose-state#11 for more information.

2.	Update FavoriteScreen to only show the user’s favorite movies.

3.	Make sure that the state is injected into the composables from outside. MovieRow should not contain a reference to the ViewModel - therefore make use of callback functions (lambda expressions in the function parameters). Users can add a new Movie to the Movie collection (AddMovieScreen). You can use the provided Composable as a template. Note: the template requires an Enum Class for Genres.

4. Extend your ViewModel with the following functionalities:
    a)	Validate user input (use onValueChange listeners to call ViewModel functions): 
        -	title (String; not empty)
        -	year (String; not empty)
        -	genres (Enum Genre; at least 1 must be selected)
        -	director (String, not empty)
        -	actors (String, not empty)
        -	plot (String)
        -	rating (Float, not empty)
    b)	Show an error text if user input is not valid
    c)	“Add” Button:
        -	Disabled by default
        -	Enable it if all user input is valid
        -	Add a movie to the collection onClick
            Note: Movie images are not required. Show a placeholder for newly added Movies in your MovieRow.




### Dependencies

    Jetpack Compose
    Jetpack Navigation
    ViewModel and LiveData
    Accompanist (for image loading)
