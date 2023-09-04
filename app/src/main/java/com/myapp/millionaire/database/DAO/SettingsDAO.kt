package com.myapp.millionaire.database.DAO

import androidx.room.Dao
import androidx.room.Query
import com.myapp.millionaire.database.entities.SettingsDB

@Dao
interface SettingsDAO {
    @Query("SELECT * FROM SettingsDB ORDER BY id LIMIT 1")
    fun getSettings(): SettingsDB
}