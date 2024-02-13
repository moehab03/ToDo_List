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
    fun insert(task: Tasks)

    @Update
    fun update(task: Tasks)

    @Delete
    fun delete(task: Tasks)

    @Query("select * from tasks")
    fun getAllTasks(): List<Tasks>

    @Query("select * from tasks where date = :date")
    fun getTasksByDate(date: Long): List<Tasks>
}