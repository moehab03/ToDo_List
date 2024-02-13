package com.route.todolist.database.data_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    var title: String?,
    @ColumnInfo
    var description: String?,
    @ColumnInfo
    var date: Long?,
    @ColumnInfo
    var isDone: Boolean?
) : Serializable
