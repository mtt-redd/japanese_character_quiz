package com.example.japanesecharacterquiz.View

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.japanesecharacterquiz.View_Model.viewModel_question
import com.example.japanesecharacterquiz.View_Model.viewmodel_user
import kotlin.random.Random

@Composable
fun Question(userviewModel: viewmodel_user = hiltViewModel(),
             questionviewModel: viewModel_question = hiltViewModel(),
             onNavigateToSelection: () -> Unit = {}) {

    //get values from user_repository
    val username = userviewModel.getusername()
    val score = userviewModel.getscore()



    val question by questionviewModel.questions.collectAsStateWithLifecycle()

    // 2. Safely check if the flow has emitted data yet
    if (question.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    val randomInd = Random.nextInt(question.size);
    Log.d("Random Size", question.size.toString())
    val currentQuestion = question[randomInd]

    val kanji = currentQuestion.Kanji

    val answers = listOf(
        currentQuestion.answer1,
        currentQuestion.answer2,
        currentQuestion.answer3,
        currentQuestion.answer4
    )
    val (selectedOption, onOptionSelected) = remember(currentQuestion) { mutableStateOf(answers[0]) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(255, 190,
            190, 255))
            .fillMaxSize()
            .padding(top = 26.dp)) {

        Row(
            modifier = Modifier.background(Color(255, 139, 139, 255))
                .border(1.dp, Color.Black, RectangleShape)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Text("Hello $username! ", textAlign = TextAlign.Left)
            Text("Score : $score ", textAlign = TextAlign.Right)
        }

        Box(modifier = Modifier.padding(top = 46.dp)
            .background
            (Color(255, 255, 255, 255))
            .border(5.dp, Color.Black,
                RectangleShape)) {
                    Text(text = kanji, fontSize = 80.sp)
                }


        Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
            Text(text = "Choose the correct answer:")
            answers.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background
                            (Color(255, 139, 139, 255))
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null,
                        modifier = Modifier.padding(20.dp),

                        enabled = true,
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp),
                    )

                }
            }


        }

        Button(colors = ButtonDefaults.outlinedButtonColors
            (containerColor = Color.Red,),
            onClick ={ onNavigateToSelection()}, ) {Text("Exit the Quiz") }
    }


    }

fun placeholder2(){

}
