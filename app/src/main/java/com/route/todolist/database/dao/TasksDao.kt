package com.route.todolist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todolist.database.data_model.Tasks

@Dao
interface TasksDao {
    @Insert
    fun insert(tasks: Tasks)

    @Update
    fun update(tasks: Tasks)

    @Delete
    fun delete(tasks: Tasks)

    @Query("select * from tasks")
    fun getAll(): List<Tasks>

    @Query("select * from tasks where date = :date")
    fun getAll(date: Long): List<Tasks>
}