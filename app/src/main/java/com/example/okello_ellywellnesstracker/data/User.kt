package com.example.okello_ellywellnesstracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "passwordHash")
    val passwordHash: String
)
