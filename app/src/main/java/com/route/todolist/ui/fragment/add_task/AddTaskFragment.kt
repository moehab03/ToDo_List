package com.route.todolist.ui.fragment.add_task

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todolist.clearTime
import com.route.todolist.database.data_model.Tasks
import com.route.todolist.database.database.TasksDatabase
import com.route.todolist.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskFragment(private var onAddClick: () -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private var date = Calendar.getInstance()

    private var day = date.get(Calendar.DAY_OF_MONTH)
    private var month = date.get(Calendar.MONTH) + 1
    private var year = date.get(Calendar.YEAR)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectedTime.text = printDate()
        saveTaskInDatabase()
        checkEditTextErrors()
        pickDateClicks()
    }

    private fun updateDate() {
        day = date.get(Calendar.DAY_OF_MONTH)
        month = date.get(Calendar.MONTH) + 1
        year = date.get(Calendar.YEAR)
    }

    private fun printDate(): String {
        return " $day / $month / $year "
    }

    private fun chooseDate() {
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, dayOfMonth ->
                date.set(Calendar.YEAR, selectedYear)
                date.set(Calendar.MONTH, selectedMonth)
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                updateDate()
                binding.selectedTime.text = printDate()
            }, year, month - 1, day
        )
        datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis
        datePicker.show()
    }

    private fun pickDateClicks() {
        binding.selectedTime.setOnClickListener {
            chooseDate()
        }
        binding.selectTimeTV.setOnClickListener {
            chooseDate()
        }
    }

    private fun saveTaskInDatabase() {
        binding.addBtn.setOnClickListener {
            if (isValid()) {
                TasksDatabase.getInstance(requireContext()).tasksDao().insert(getTask())
                onAddClick.invoke()
                dismiss()
            }
        }
    }

    private fun isValid(): Boolean {
        var isValid = true
        if (binding.titleET.editText!!.text.isEmpty()) {
            binding.titleET.error = "Please enter title"
            isValid = false
        }
        if (binding.descriptionET.editText!!.text.isEmpty()) {
            binding.descriptionET.error = "Please enter description"
            isValid = false
        }
        return isValid
    }

    private fun checkEditTextErrors() {
        binding.titleET.editText!!.addTextChangedListener {
            binding.titleET.error = null
        }
        binding.descriptionET.editText!!.addTextChangedListener {
            binding.descriptionET.error = null
        }
    }

    private fun getTask(): Tasks {
        val title = binding.titleET.editText!!.text.toString()
        val description = binding.descriptionET.editText!!.text.toString()
        date.clearTime()
        val taskDate = date.timeInMillis
        return Tasks(title = title, description = description, date = taskDate, isDone = false)
    }
}