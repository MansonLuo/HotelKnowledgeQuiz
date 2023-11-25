package com.example.hotelknowledgequiz

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.hotelknowledgequiz.ui.screens.choicequestion.ChoiceQuestion
import com.example.hotelknowledgequiz.ui.screens.choicequestion.handlerToIndex

class MainViewModel: ViewModel() {


    val questions = listOf(
        ChoiceQuestion(
            question = "目前我国境内已知最早的人类是？",
            answers = listOf(
                "A: 元谋人", "B: 北京人", "C: 山顶洞人", "D: 半坡原始居民"
            ),
            correctAnswerIndex = handlerToIndex("C")
        ),
        ChoiceQuestion(
            question = "1945年8月至10月，国共双方举行了商讨和平建国方针的？",
            answers = listOf(
                "A: 重庆谈判",
                "B: 西安谈判",
                "C: 南京谈判",
                "D: 北平谈判"
            ),
            correctAnswerIndex = handlerToIndex("A")
        ),
        ChoiceQuestion(
            question = "我国对资本主义工商业进行社会主义改造所实行的高级形式的国家资本主义是？",
            answers = listOf(
                "A: 加工订货",
                "B: 统购统销",
                "C: 经销代销",
                "D: 公私合营"
            ),
            correctAnswerIndex = handlerToIndex("D")
        )
    )

    val colorsList = mutableStateListOf<SnapshotStateList<Color>>()

    init {
        repeat(questions.size) {
            colorsList.add(
                mutableStateListOf(
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                )
            )
        }
    }

    fun revealAnswer(
        questionIndex: Int,
        clickedAnswerIndex: Int
    ) {
        val currentCorrectAnswerIndex = questions[questionIndex].correctAnswerIndex

        if (currentCorrectAnswerIndex == clickedAnswerIndex) {
            colorsList[questionIndex][clickedAnswerIndex] = correctAnswerFontColor
        } else {
            colorsList[questionIndex][clickedAnswerIndex] = wrongAnswerFontColor
            colorsList[questionIndex][currentCorrectAnswerIndex] = correctAnswerFontColor
        }
    }
}