package com.route.todolist.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.route.todolist.R
import com.route.todolist.database.data_model.Tasks
import com.route.todolist.databinding.TaskDesignBinding

class TasksAdapter(private val tasks: List<Tasks>) : Adapter<TasksAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: TaskDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val task = tasks[position]
            binding.titleTv.text = task.title
            binding.timeTv.text = task.date
            binding.doneBtn.setOnClickListener {
                if (!task.isDone!!)
                    taskDone(holder, task)
                else
                    undoTask(holder, task)
            }
        }
    }

    private fun undoTask(holder: ViewHolder, task: Tasks) {
        task.isDone = false
        with(holder) {
            binding.line.setImageResource(R.color.cornflower_blue)
            binding.titleTv.setTextColor(Color.parseColor("#5D9CEC"))
            binding.doneBtn.setImageResource(R.drawable.undone_btn_img)
        }
    }

    private fun taskDone(holder: ViewHolder, task: Tasks) {
        task.isDone = true
        with(holder) {
            binding.line.setImageResource(R.color.mint_green)
            binding.titleTv.setTextColor(Color.parseColor("#61E757"))
            binding.doneBtn.setImageResource(R.drawable.done_btn_img)
        }
    }
}