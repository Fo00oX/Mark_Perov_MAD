package com.example.lectureexamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lectureexamples.navigation.Navigation
import com.example.lectureexamples.ui.theme.MyMovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMovieTheme {
                Navigation()
            }
        }
    }
}


