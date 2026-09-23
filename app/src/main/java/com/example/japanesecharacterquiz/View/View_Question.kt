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
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.japanesecharacterquiz.View_Model.viewModel_question
import com.example.japanesecharacterquiz.View_Model.viewmodel_user

@Composable
fun Question(userviewModel: viewmodel_user = hiltViewModel(),
             questionviewModel: viewModel_question = hiltViewModel(),
             onNavigateToSelection: () -> Unit = {}) {

    //get values from user_repository
    val username = userviewModel.getusername()
    val score = userviewModel.getscore()

    //questionviewModel.setquestion(1)

   var answers = listOf<String>("loading", "loading", "loading", "loading")
    var question = "loading"

    LaunchedEffect(Unit) {

        questionviewModel.setquestion(1)
        answers = questionviewModel.retriveanswers()
        question = questionviewModel.retrivequestion()
        Log.d("View_Couritine", answers.toString() )
    }
    Log.d("View", answers.toString())

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(answers[0]) }

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
            Text(question, fontSize = 80.sp)
        }

        Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
            Text(text = "Select a difficulty")
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
