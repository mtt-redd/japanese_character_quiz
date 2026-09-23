package com.example.japanesecharacterquiz.View

import com.example.japanesecharacterquiz.View_Model.viewmodel_user
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.japanesecharacterquiz.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel


@Composable
    fun Greeting(viewModel: viewmodel_user = hiltViewModel(),
                 onNavigateToSelection: () -> Unit = {}) {
    // Look for event from checkuser() in viewmodel
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect {
            onNavigateToSelection()
        }
    }

        //background image. Blurred to look better. Free license
        Image(
            painter = painterResource(id =R.drawable.pexels_daniele_ursino_2150650320_31252768),
            contentDescription = "Japanese Background",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
                .blur(1.5.dp)
        )

        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image( //Title. A png with invisible background
                painter = painterResource(id =R.drawable.illustration),
                contentDescription = "Title",
                modifier = Modifier.size(250.dp),
                contentScale = ContentScale.Fit,


                )

            Box(Modifier.padding(56.dp)  //rounded box. Pink colour
                .clip(RoundedCornerShape(16.dp))
                .background(Color(255, 192, 203))
                .fillMaxWidth()
                .size(250.dp),
            ){
                Column(Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Welcome, please add your username here!",

                        )
                    val inputuser = rememberTextFieldState()
                    val inputtext = inputuser.text.toString()
                    val inputempty = !inputtext.isBlank()
                    TextField(
                        state = inputuser,
                        placeholder = {Text("User")},
                        label = { Text("Username") },
                        modifier = Modifier.padding(10.dp))
                    Button (
                        onClick = {
                            viewModel.checkuser(inputtext)
                                  },

                        Modifier.padding(5.dp)
                            .size(100.dp),
                        enabled = inputempty, //checks if user filled the textfield
                    ) {
                        Text("Submit")

                    }



                }}}


    }
