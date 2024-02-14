package com.route.todolist.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.route.todolist.R
import com.route.todolist.database.data_model.Tasks
import com.route.todolist.databinding.TaskDesignBinding

class TasksAdapter(
    private var tasks: List<Tasks>,
    private val updateTask: (task: Tasks) -> Unit,
    private val onTaskClick: (task: Tasks) -> Unit
) :
    Adapter<TasksAdapter.ViewHolder>() {
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
            with(binding)
            {
                titleTv.text = task.title
                descriptionTv.text = task.description
                doneBtn.setOnClickListener {
                    if (!task.isDone!!)
                        taskDone(holder, task)
                    else
                        undoTask(holder, task)
                }
                itemView.setOnClickListener {
                    onTaskClick(task)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(tasksList: List<Tasks>) {
        tasks = tasksList
        notifyDataSetChanged()
    }

    private fun undoTask(holder: ViewHolder, task: Tasks) {
        task.isDone = false
        updateTask.invoke(task)
        with(holder) {
            binding.line.setImageResource(R.color.cornflower_blue)
            binding.titleTv.setTextColor(Color.parseColor("#5D9CEC"))
            binding.doneBtn.setImageResource(R.drawable.undone_btn_img)
        }
    }

    private fun taskDone(holder: ViewHolder, task: Tasks) {
        task.isDone = true
        updateTask.invoke(task)
        with(holder) {
            with(binding) {
                line.setImageResource(R.color.mint_green)
                titleTv.setTextColor(Color.parseColor("#61E757"))
                doneBtn.setImageResource(R.drawable.done_btn_img)
            }
        }
    }
}