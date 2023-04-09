package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.components.MovieViewModel
import com.example.movieapp.navigation.Navigation
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    movieViewModel = viewModel()
                    navController = rememberNavController()
                    Column {
                        Navigation(movieViewModel, navController)
                    }
                }
            }
        }
    }
}