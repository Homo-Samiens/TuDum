package com.example.tudum.data

import androidx.room.Database
import androidx.room.RoomDatabase

//Database

@Database(
    entities = [Todo::class],
    version = 1
)

abstract class Database: RoomDatabase() {

    abstract val dao: Dao
}