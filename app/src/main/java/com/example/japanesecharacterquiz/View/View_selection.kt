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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.japanesecharacterquiz.View_Model.viewModel_question
import com.example.japanesecharacterquiz.View_Model.viewmodel_user
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun Selection(viewModel: viewmodel_user = hiltViewModel(),
              questionviewModel: viewModel_question = hiltViewModel(),
              onNavigateToLogin: () -> Unit = {},
              onNavigateToQuiz: () -> Unit = {},
              ) {

    //context to load the database
    //get values from user_repository
    val username = viewModel.getusername()
    val score by viewModel.score.collectAsStateWithLifecycle()
    //get values from input
    var checked by remember { mutableStateOf(true) }

    Log.d("", "Are you online?")

    val radioOptions = listOf("Easy", "Normal", "Hard")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    val showDialog by viewModel.showDialog.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            onNavigateToLogin()
        }
    }

    val configuration = LocalConfiguration.current
    val context = LocalActivity.current


//block changing orientation
    LaunchedEffect(configuration) {
        val activity = context ?: return@LaunchedEffect
        val isCompact = configuration.screenWidthDp < 600 || configuration.screenHeightDp < 600
        activity.requestedOrientation = if (isCompact) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        }
    }

Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(26.dp),
    modifier = Modifier.background(Color(255, 190,
        190, 255))
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(top = 26.dp)) {
    Row(
modifier = Modifier.background(Color(255, 139, 139, 255))
    .border(1.dp, Color.Black, RectangleShape)
    .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text("Hello $username!", textAlign = TextAlign.Left)
        Text("Score : $score", textAlign = TextAlign.Right)
    }

Button(onClick ={ onNavigateToQuiz()}, ) {Text("START!") }

    Column(modifier = Modifier.padding(top = 26.dp)){

        Box(modifier = Modifier.border(1.dp, Color.Black,
            RectangleShape)) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text("Answers in Hiragana?")
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    },)

            }


        }}


            Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
                Text(text = "Select a difficulty")
                radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                RadioButton(
                    selected = (text == selectedOption ),
                    onClick = null,
                    modifier = Modifier.padding(20.dp),

                    enabled = true,
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 16.dp),
                )

            }}
questionviewModel.setdifficulty(selectedOption)

        }

    if (checked == true){
        questionviewModel.enableHira()
    }
    else {questionviewModel.disableHira()}




    Button(colors = ButtonDefaults.outlinedButtonColors
        (containerColor = Color.Red),
        onClick ={ onNavigateToLogin()}, ) {Text("Log Out") }


    TextButton(
        onClick = {viewModel.onOpenDialogClicked()},
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color(0xFF0083FF),
            containerColor = Color.Transparent       // Background color (transparent by default)
        )
    ) {
        Text("Delete Account")
    }

    if (showDialog) {
        DeleteDialog(
            onDismiss = viewModel::onDialogDismissed,
            onConfirm = viewModel::onDialogConfirmed,
        )
    }



    }


}

@Composable
fun DeleteDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss, // Handles back button or outside touch clicks
        title = { Text(text = "Delete Account?") },
        text = { Text(text = "Are you sure you want to proceed? " +
                "This action cannot be reversed") },
        confirmButton = {
            TextButton(onClick = onConfirm ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}

