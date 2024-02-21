package com.route.todolist.ui.update

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import com.route.todolist.Constant
import com.route.todolist.R
import com.route.todolist.clearTime
import com.route.todolist.database.data_model.Tasks
import com.route.todolist.database.database.TasksDatabase
import com.route.todolist.databinding.ActivityUpdateBinding
import com.route.todolist.databinding.TaskDesignBinding
import com.route.todolist.ui.fragment.list.ListFragment
import com.route.todolist.ui.home.HomeActivity
import java.util.Calendar

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var task: Tasks

    private var date = Calendar.getInstance()
    private var day = date.get(Calendar.DAY_OF_MONTH)
    private var month = date.get(Calendar.MONTH) + 1
    private var year = date.get(Calendar.YEAR)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getTask()
        setDataInFields()
        checkEditTextErrors()
        pickDateClicks()
        updateTaskClick()
    }

    private fun updateTaskClick() {
        binding.updateBtn.setOnClickListener {
            if (updateTask())
                finish()
        }
    }

    private fun updateTask(): Boolean {
        if (isValid()) {
            with(binding) {
                task.title = titleUpdateET.editText!!.text.toString()
                task.description = descriptionUpdateET.editText!!.text.toString()
                date.clearTime()
                task.date = date.timeInMillis
                TasksDatabase.getInstance(this@UpdateActivity).tasksDao().update(task)
                return true
            }
        } else
            return false
    }

    private fun isValid(): Boolean {
        var isValid = true
        if (binding.titleUpdateET.editText!!.text.isEmpty()) {
            binding.titleUpdateET.error = "Please enter title"
            isValid = false
        }
        if (binding.descriptionUpdateET.editText!!.text.isEmpty()) {
            binding.descriptionUpdateET.error = "Please enter description"
            isValid = false
        }
        return isValid
    }

    private fun checkEditTextErrors() {
        binding.titleUpdateET.editText!!.addTextChangedListener {
            binding.titleUpdateET.error = null
        }
        binding.descriptionUpdateET.editText!!.addTextChangedListener {
            binding.descriptionUpdateET.error = null
        }
    }

    private fun setDataInFields() {
        with(binding) {
            titleUpdateET.editText!!.setText(task.title)
            descriptionUpdateET.editText!!.setText(task.description)
            selectedTimeUpdate.text = printDate()
        }
    }

    private fun updateDate() {
        day = date.get(Calendar.DAY_OF_MONTH)
        month = date.get(Calendar.MONTH) + 1
        year = date.get(Calendar.YEAR)
        binding.selectedTimeUpdate.text = printDate()
    }

    private fun printDate(): String {
        return " $day / $month / $year "
    }

    private fun chooseDate() {
        val datePicker = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, dayOfMonth ->
                date.set(Calendar.YEAR, selectedYear)
                date.set(Calendar.MONTH, selectedMonth)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()
            }, year, month - 1, day
        )
        datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis
        datePicker.show()
    }

    private fun pickDateClicks() {
        binding.selectTimeUpdateTV.setOnClickListener {
            chooseDate()
        }
        binding.selectedTimeUpdate.setOnClickListener {
            chooseDate()
        }
    }

    private fun getTask() {
        task = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Constant.TASK, Tasks::class.java)
        } else {
            intent.getSerializableExtra(Constant.TASK) as Tasks
        }!!
    }
}