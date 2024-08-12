package com.example.carfax.data.local_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class], version = 2)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}