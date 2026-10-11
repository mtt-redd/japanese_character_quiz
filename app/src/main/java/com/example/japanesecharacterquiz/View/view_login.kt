package com.example.japanesecharacterquiz.View

import android.content.pm.ActivityInfo
import androidx.activity.compose.LocalActivity
import com.example.japanesecharacterquiz.View_Model.viewmodel_user
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
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

//Used to register the configuration and the context
    val configuration = LocalConfiguration.current
    val context = LocalActivity.current

    //Avoid changing the screen when rotating the device.
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
//Simple box which defines the Aspect Ratio.
    Box(Modifier.fillMaxSize()
        .aspectRatio(9f / 16f),
    ){
        //background image. Blurred to look better. Free license
        Image(
            painter = painterResource(id =R.drawable.pexels_daniele_ursino_2150650320_31252768),
            contentDescription = "Japanese Background",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
                .blur(1.5.dp) //Image is slightly blurred
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image( //Title. A png with invisible background
                painter = painterResource(id =R.drawable.illustration),
                contentDescription = "Title",
                modifier = Modifier.fillMaxWidth(0.75f)
                    .padding(20.dp),
                contentScale = ContentScale.Fit,


                )

            Box(Modifier.padding(56.dp)  //rounded box. Pink colour
                .clip(RoundedCornerShape(16.dp))
                .background(Color(255, 192, 203))
                .fillMaxWidth(0.90f),
            ){
                //Text field to add the user
                Column(Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Welcome, please add your username here!",

                        )
                    //Variables hold the input from the user
                    val inputUser = rememberTextFieldState()
                    val inputText = inputUser.text.toString()
                    //Return False is InputText is empty.
                    val inputEmpty = !inputText.isBlank()
                    TextField(
                        state = inputUser,
                        placeholder = { Text("User") },
                        //Username label, also used by the UnitTest
                        label = { Text("Username") },
                        modifier = Modifier.padding(10.dp)
                    )
                    Button(
                        onClick = {
                            viewModel.checkuser(inputText)
                        },

                        Modifier.padding(5.dp)
                            .fillMaxWidth(0.75f),
                        enabled = inputEmpty, //Checks if user filled the textfield
                    ) {
                        Text("Submit")

                    }


                }}}}


    }
