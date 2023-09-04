package com.myapp.millionaire.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.millionaire.database.entities.SettingsDB

@Database(entities = [SettingsDB::class], version = 1)
abstract class ApplicationDB: RoomDatabase() {
    abstract class SettingsDAO();
}