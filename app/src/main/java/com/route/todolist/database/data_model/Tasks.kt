package com.route.todolist.database.data_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    var title: String?,
    @ColumnInfo
    var description: String?,
    @ColumnInfo
    var date: String?,
    @ColumnInfo
    var isDone: Boolean?
)
