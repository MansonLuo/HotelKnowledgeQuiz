package com.example.hotelknowledgequiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hotelknowledgequiz.ui.screens.choicequestion.AllChoiceQuestionsUI
import com.example.hotelknowledgequiz.ui.theme.HotelKnowledgeQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelKnowledgeQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Preview
@Composable
fun App() {
    val mainViewModel = viewModel<MainViewModel>()

    AllChoiceQuestionsUI(
        questions = mainViewModel.questions,
        colorsList = mainViewModel.colorsList,
        onAnswerRevealed = mainViewModel::revealAnswer
    )
}
