package com.example.japanesecharacterquiz.Database

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import java.util.Date

@Entity(tableName = "QuestionTable")

class question (
    @PrimaryKey(autoGenerate = true,)
    val id: Int,
    val Kanji: String, //The Kanji that will be asked.
    val answer1: String, //First answer in English
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val answer1Hiragana: String, //First answer in Hiragana
    val answer2Hiragana: String,
    val answer3Hiragana: String,
    val answer4Hiragana: String,
    val correctanswer: Int, //Id of the correct answer
    val hint: String, //Hint displayed when asked.
    val wrong: String, //Text that appears when the question was answered incorrectly.
    val difficulty: Int //Difficulty of the Kanji
)
