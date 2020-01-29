package com.example.kotlinmvvm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(@PrimaryKey(autoGenerate = true) var id:Int = 0, val title:String, val description:String, val priority:Int)

