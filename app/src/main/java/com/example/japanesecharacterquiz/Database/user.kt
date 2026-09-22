package com.example.japanesecharacterquiz.Database

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey
import java.util.Date

@Entity(tableName = "User_Table",
    indices = [Index(
        value = ["username"],
        unique = true
    )])

class user (
    @PrimaryKey(autoGenerate = true,)
    val id: Int,
    @ColumnInfo(name = "username") val username: String,
    val score: Int) {


}