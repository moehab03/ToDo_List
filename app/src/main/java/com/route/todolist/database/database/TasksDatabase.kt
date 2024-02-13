package com.route.todolist.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todolist.database.dao.TasksDao
import com.route.todolist.database.data_model.Tasks

@Database(entities = [Tasks::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        private var database: TasksDatabase? = null
        private const val DATABASE_NAME = "tasks_database"
        fun getInstance(context: Context): TasksDatabase {

            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    TasksDatabase::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries()
                    .build()
            }

            return database!!
        }
    }

}