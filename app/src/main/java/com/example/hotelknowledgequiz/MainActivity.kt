package com.example.hotelknowledgequiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotelknowledgequiz.ui.theme.HotelKnowledgeQuizTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelKnowledgeQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun App() {
    val list = listOf(
        "quetions 1",
        "question 2",
        "question 3",
        "question 4"
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        list.size
    }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = list.get(it),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Green)
            )
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                modifier = Modifier.padding(end = 8.dp),
                enabled = pagerState.currentPage != 0
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    ""
                )
            }
            Button(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                modifier = Modifier
                    .padding(start = 8.dp),
                enabled = pagerState.currentPage != pagerState.pageCount - 1
            ) {
                Icon(
                    Icons.Filled.ArrowForward,
                    ""
                )
            }
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iterate ->
                val color =
                    if (iterate == pagerState.currentPage) Color.DarkGray else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color = color)
                        .size(16.dp)
                )
            }
        }
    }
}

@Composable
fun ChoiceQuestionUI(
    choiceQuestion: ChoiceQuestion
) {
    val correctAnswerColor = Color.Green
    val wrongAnswerColor = Color.Red
    val answersFontColors = remember {
        mutableStateListOf(
            Color.Black,
            Color.Black,
            Color.Black,
            Color.Black
        )
    }

    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = choiceQuestion.question,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            repeat(4) {
                Text(
                    text = choiceQuestion.answers.get(it),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .clickable {
                            if (choiceQuestion.correctAnswerIndex == it) {
                                answersFontColors.set(it, correctAnswerColor)
                            } else {
                                answersFontColors.set(it, wrongAnswerColor)
                                answersFontColors.set(choiceQuestion.correctAnswerIndex, correctAnswerColor)
                            }
                        },
                    color = answersFontColors.get(it)
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_ChoiceQuestionUI() {
    val choiceQuestion = ChoiceQuestion(
        question = "目前我国境内已知最早的人类是？",
        answers = listOf(
            "A: 元谋人",
            "B: 北京人",
            "C: 山顶洞人",
            "D: 半坡原始居民"
        ),
        correctAnswerIndex = handlerToIndex("C")
    )
    
    ChoiceQuestionUI(choiceQuestion = choiceQuestion)
}
