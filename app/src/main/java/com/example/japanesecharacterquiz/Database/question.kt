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
    val Kanji: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val answer1Hiragana: String,
    val answer2Hiragana: String,
    val answer3Hiragana: String,
    val answer4Hiragana: String,
    val correctanswer: Int,
    val hint: String,
    val wrong: String,
    val difficulty: Int
)
