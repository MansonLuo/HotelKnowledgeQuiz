package com.example.hotelknowledgequiz

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