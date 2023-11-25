package com.example.hotelknowledgequiz.ui.screens.choicequestion

import androidx.compose.ui.graphics.Color

data class ChoiceQuestion(
    val question: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

fun handlerToIndex(handler: String): Int {
    return when(handler)  {
        "A" -> 0
        "B" -> 1
        "C" -> 2
        "D" -> 3
        else -> -1
    }
}

fun generateDefaultOptionsColors(number: Int): List<List<Color>> {
    val allColors = mutableListOf<List<Color>>()

    repeat(number) {
        allColors.add(
            listOf(
                Color.Black,
                Color.Black,
                Color.Black,
                Color.Black
            )
        )
    }

    return allColors.toList()
}