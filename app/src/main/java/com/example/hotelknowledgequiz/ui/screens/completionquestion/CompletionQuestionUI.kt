package com.example.hotelknowledgequiz.ui.screens.completionquestion

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CompletionQuestionUI(
    question: CompletionQuestion
) {
    Card {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                question.question.subList(0, question.question.size - 1).forEach {  partStr ->
                    Text(text = partStr)
                    CustomTextField(text = "", onTextChange = { txt ->})
                }
                Text(text = question.question.last())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_CompletionQuestionUI() {
    val question = CompletionQuestion(
        question = listOf("我国的四大发明:", ",", ",", ",", "。"),
        answer = listOf("西安事变")
    )
    
    CompletionQuestionUI(
        question = question
    )
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize
) {

    var text by rememberSaveable {
        mutableStateOf("")
    }

    BasicTextField(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.small
            ),
        value = text,
        onValueChange = onTextChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = fontSize
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    innerTextField()
                }
            }
        }
    )
}