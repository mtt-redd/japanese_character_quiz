package com.example.japanesecharacterquiz.View

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
import androidx.compose.foundation.selection.selectable
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.japanesecharacterquiz.View_Model.viewmodel_user

@Composable
fun Selection(viewModel: viewmodel_user = hiltViewModel(), onNavigateToLogin: () -> Unit = {}) {

    //context to load the database
    val context = LocalContext.current
    //get values from user_repository
    val username = viewModel.getusername()
     val score = viewModel.getscore()
    //get values from input
    var checked by remember { mutableStateOf(true) }

    val radioOptions = listOf("Easy", "Normal", "Hard")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(26.dp),
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
        Text("Hello $username!", textAlign = TextAlign.Left)
        Text("Score : $score", textAlign = TextAlign.Right)
    }

Button(onClick ={ placeholder()}, ) {Text("START!") }

    Column(modifier = Modifier.padding(top = 26.dp)){

        Box(modifier = Modifier.border(1.dp, Color.Black,
            RectangleShape)) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text("Answer in Hiragana?")
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


        }


    Button(colors = ButtonDefaults.outlinedButtonColors
        (containerColor = Color.Red),
        onClick ={ onNavigateToLogin()}, ) {Text("Log Out") }

    Button(colors = ButtonDefaults.outlinedButtonColors
        (containerColor = Color.Red),
        onClick ={ viewModel.deleteuser()
            onNavigateToLogin()}, ) {Text("Delete account") }

    }



}



fun placeholder(){

}