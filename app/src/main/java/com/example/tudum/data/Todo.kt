package com.example.tudum.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Data Model

@Entity
data class Todo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)