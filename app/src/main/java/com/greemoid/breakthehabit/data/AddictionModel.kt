package com.greemoid.breakthehabit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.greemoid.breakthehabit.presentation.AddictionUiModel

@Entity(tableName = "addiction_table")
data class AddictionModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val days: String,
    @ColumnInfo val date: String,
    @ColumnInfo val image: String,
) {
    fun to(): AddictionUiModel {
        return AddictionUiModel(
            days,
            date,
            image
        )
    }
}
