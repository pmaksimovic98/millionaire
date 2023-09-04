package com.myapp.millionaire.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SettingsDB(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "game_level_value")
    val gameLevelValue: Int?,
    @ColumnInfo(name = "sound_value")
    val soundValue: Int?,
)