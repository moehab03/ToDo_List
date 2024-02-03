package com.route.todolist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.route.todolist.database.data_model.Tasks
import com.route.todolist.databinding.TaskDesignBinding

class TasksAdapter(private val tasks: List<Tasks>) : Adapter<TasksAdapter.ViewHolder>() {
    private lateinit var binding: TaskDesignBinding

    class ViewHolder(binding: TaskDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        binding.titleTv.text = task.title
    }
}