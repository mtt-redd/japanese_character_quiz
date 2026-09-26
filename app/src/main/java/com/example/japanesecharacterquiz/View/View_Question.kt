package com.example.japanesecharacterquiz.View

import android.content.pm.ActivityInfo
import android.util.Log
import androidx.activity.compose.LocalActivity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.material3.ConfirmationDialog
import com.example.japanesecharacterquiz.Database.question
import com.example.japanesecharacterquiz.View_Model.viewModel_question
import com.example.japanesecharacterquiz.View_Model.viewmodel_user
import kotlin.random.Random

//Simple variable to change the point.
// TODO: Might be used to give different weight to different questions
val pointReward = 50


@Composable
fun Question(userviewModel: viewmodel_user = hiltViewModel(),
             questionviewModel: viewModel_question = hiltViewModel(),
             onNavigateToSelection: () -> Unit = {}) {

    //get values from user_repository
    val username = userviewModel.getusername()

    val score by userviewModel.score.collectAsStateWithLifecycle()





    val questions by questionviewModel.questions.collectAsStateWithLifecycle()


    val kanji = questions.Kanji




    var answers = listOf(
        questions.answer1,
        questions.answer2,
        questions.answer3,
        questions.answer4
    )

if (questionviewModel.getHira() == true){
Log.d("", "Enable Hira")
        answers = listOf(
            questions.answer1Hiragana,
            questions.answer2Hiragana,
            questions.answer3Hiragana,
            questions.answer4Hiragana,)

    }
    val (selectedOption, onOptionSelected) =
        remember(questions)
        { mutableStateOf(answers[0]) }

    val wrongcheck by questionviewModel.wrongcheck
        .collectAsStateWithLifecycle()

    val showDialog by questionviewModel.showDialog
        .collectAsStateWithLifecycle()

    val showWrongDialog by questionviewModel.showWrongDialog
        .collectAsStateWithLifecycle()

    val showHintDialog by questionviewModel.showHintDialog
        .collectAsStateWithLifecycle()

    val configuration = LocalConfiguration.current
    val context = LocalActivity.current

    LaunchedEffect(configuration) {
        val activity = context ?: return@LaunchedEffect
        // Determine if screen is compact (phone-sized) in either width or height
        val isCompact = configuration.screenWidthDp < 600 || configuration.screenHeightDp < 600
        activity.requestedOrientation = if (isCompact) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(255, 190,
            190, 255))
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 26.dp)) {

        Row(
            modifier = Modifier.background(Color(255, 139, 139, 255))
                .border(1.dp, Color.Black, RectangleShape)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Hello $username! ", textAlign = TextAlign.Left)
            Text("Score : $score ", textAlign = TextAlign.Right)
        }

        TextButton(
            onClick = {questionviewModel.openHintDialog()},
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xFF0083FF),
                containerColor = Color.Transparent       // Background color (transparent by default)
            )
        ) {
            Text("Hint")
        }

        if (wrongcheck) {
            Text(
                text = "You got this question wrong before!",
                )
        }

        Box(modifier = Modifier.padding(top = 10.dp)
            .background
            (Color(255, 255, 255, 255))
            .border(5.dp, Color.Black,
                RectangleShape)) {
                    Text(text = kanji, fontSize = 60.sp)
                }


        Column (verticalArrangement = Arrangement.spacedBy(5.dp)){
            Text(text = "Choose the correct answer:")
            answers.forEachIndexed { index, text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background
                            (Color(255, 139, 139, 255))
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text)
                            questionviewModel.setSelectedAnswer(index)}
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
            (containerColor = Color.Blue,),
            onClick ={ questionviewModel.verifyanswer()}, ) {Text("Submit!") }


    Button(colors = ButtonDefaults.outlinedButtonColors
            (containerColor = Color.Red,),
            onClick ={ onNavigateToSelection()}, ) {Text("Exit the Quiz") }
    }

    if (showDialog) {
        ConfirmationDialog(
            onDismiss = { questionviewModel.onDialogDismissed()
                questionviewModel.loadNewQuestion()
            userviewModel.updatescore(pointReward, score)
            Log.d("View","This is happening")}
        )
    }

    if (showWrongDialog){
        WrongDialog(
            onWrongDismiss = {
                questionviewModel.onWrongDialogDismissed()
                questionviewModel.setWrongQuestion(questions.id)
                Log.d("View - ShowWrongDiaolog", "RetriveWrongQuestion")
                questionviewModel.loadNewQuestion()
            },
            explanation = questions.wrong
        )
    }

    if (showHintDialog){
        HintDialog(
            onHintDismiss = {
                questionviewModel.onHintDialogDismissed()
            },
            hint = questions.hint
        )
    }


    }

@Composable
fun ConfirmationDialog(
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss, // Handles back button or outside touch clicks
        title = { Text(text = "You got it right!") },
        text = { Text(text = "Congratulation, $pointReward+ points") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Ok")
            }
        }
    )
}

@Composable
fun WrongDialog(
    onWrongDismiss: () -> Unit,
    explanation : String
) {
    AlertDialog(
        onDismissRequest = onWrongDismiss, // Handles back button or outside touch clicks
        title = { Text(text = "You got it wrong.") },
        text = { Text(text = explanation) },
        confirmButton = {
            TextButton(onClick = onWrongDismiss) {
                Text("Ok")
            }
        }
    )
}

@Composable
fun HintDialog(
    onHintDismiss: () -> Unit,
    hint : String
) {
    AlertDialog(
        onDismissRequest = onHintDismiss, // Handles back button or outside touch clicks
        title = { Text(text = "Here is your hint:") },
        text = { Text(text = hint) },
        confirmButton = {
            TextButton(onClick = onHintDismiss) {
                Text("Ok")
            }
        }
    )
}


