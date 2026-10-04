package com.example.japanesecharacterquiz

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.isSelectable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.japanesecharacterquiz.View.Greeting
import com.example.japanesecharacterquiz.View.Question
import com.example.japanesecharacterquiz.View.Selection
import com.example.japanesecharacterquiz.View_Model.viewModel_question
import com.example.japanesecharacterquiz.View_Model.viewmodel_user
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableSharedFlow
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest

class firstest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<HiltTestActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    //Check if the Button is disabled when the input is empty

    @Test
    fun submitButton_isDisabled_whenUsernameIsEmpty() {
        composeRule.setContent {
            Greeting()
        }

        composeRule.onNodeWithText("Submit").assertIsNotEnabled()
    }

    //Check if the Button is Enabled when the input is added

    @Test
    fun submitButton_isEnabled_whenUsernameIsAdded() {
        composeRule.setContent {
            Greeting()
        }

        val name = "Test"
        composeRule.onNodeWithText("Username").performTextInput(name)
        composeRule.onNodeWithText("Submit").assertIsEnabled()
    }

    //verify if the name and score are added
    @Test
    fun verifyNameAndScoreAreAdded() {
    //Retrive the viewmodel, so we can retrieve the same values the View uses
        val viewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]

        composeRule.setContent { Selection() }
        composeRule.waitForIdle()

        val testName = viewModel.getusername()
        val testScore = viewModel.score.value

        composeRule.onNodeWithText("Hello $testName!").assertIsDisplayed()
        composeRule.onNodeWithText("Score : $testScore").assertIsDisplayed()
    }

    @Test
    fun verifyNewQuestionIsLoaded() {
        //Retrive the viewmodel, so we can retrieve the same values the View uses
        //Use both Viewmodels
        val userViewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]
        val questionViewModel = ViewModelProvider(composeRule.activity)[viewModel_question::class.java]

        composeRule.setContent { Question() }

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodes(isSelectable()).fetchSemanticsNodes().isNotEmpty()
        }

        val previousQuestion = questionViewModel.questions.value.Kanji

        composeRule.onAllNodes(isSelectable())[1].performClick()

        composeRule.onNodeWithText("Submit!").performClick()

        composeRule.onNodeWithText("Ok").performClick()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodes(isSelectable()).fetchSemanticsNodes().isNotEmpty()
        }

        assertNotEquals(previousQuestion,questionViewModel.questions.value.Kanji )
    }

    @Test
    fun checkQuestionDifficulty(){

        val userViewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]
        val questionViewModel = ViewModelProvider(composeRule.activity)[viewModel_question::class.java]

        composeRule.setContent { Selection() }

        composeRule.onAllNodes(isSelectable())[2].performClick()

        composeRule.onNodeWithText("START!").performClick()

        val difficulty = questionViewModel.getDifficulty()

        assertEquals(3, difficulty )


    }

    @Test
    fun assertQuestionHira(){

        val userViewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]
        val questionViewModel = ViewModelProvider(composeRule.activity)[viewModel_question::class.java]

        composeRule.setContent { Selection() }

        composeRule.onNodeWithTag("Hiragana").performClick()

        composeRule.onNodeWithText("START!").performClick()

        val typeOfScript = questionViewModel.getHira()

        assertEquals(false, typeOfScript )


    }

    @Test
    fun verifyUserIsAdded(){

        val userViewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]
        val questionViewModel = ViewModelProvider(composeRule.activity)[viewModel_question::class.java]

        userViewModel.addTestUser()

        composeRule.setContent { Selection() }

        val newvalue = userViewModel.getusername()

        assertEquals("TestUser", newvalue)

    }

    @Test
    fun verifyUserIsDeleted() {

        val userViewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]
        val questionViewModel =
            ViewModelProvider(composeRule.activity)[viewModel_question::class.java]

        userViewModel.addTestUser()

        composeRule.setContent { Selection() }

        composeRule.onNodeWithText("Delete Account").performClick()

        composeRule.onNodeWithText("Yes").performClick()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            userViewModel.getusername().isEmpty()
        }

        val deletedvalue = userViewModel.getusername().isEmpty()

        //returns username.isNotEmpty Boolean
        assertEquals(true, deletedvalue)


    }

    @Test
    fun verifyWrongAnswerisSaved(){

        val userViewModel = ViewModelProvider(composeRule.activity)[viewmodel_user::class.java]
        val questionViewModel = ViewModelProvider(composeRule.activity)[viewModel_question::class.java]

        composeRule.setContent { Question() }

        var index = 1

        composeRule.waitUntil(timeoutMillis = 5_000) {
            questionViewModel.questions.value.id != 0
        }

        var questionid = questionViewModel.questions.value.id

        //if the correct answer is 1, we use index 2 instead to choose the incorrect question
        if (questionViewModel.questions.value.correctanswer == 1){

            index = 2
        }

        composeRule.onAllNodes(isSelectable())[index - 1].performClick()

        composeRule.onNodeWithText("Submit!").performClick()

        composeRule.onNodeWithText("Ok").performClick()

        assertEquals(questionid, questionViewModel.testGetWrongQuestion())


    }


}

