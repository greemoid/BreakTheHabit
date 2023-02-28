package com.greemoid.breakthehabit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addiction_table")
data class AddictionModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val days: String,
    @ColumnInfo val why: String,
    @ColumnInfo val date: String,
    @ColumnInfo val image: String,
)
