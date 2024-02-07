package com.route.todolist.ui.fragment.add_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todolist.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddTaskBinding
    private var date = Calendar.DATE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterData()
        checkEditTextErrors()
        pickDateListener()
    }

    private fun checkEditTextErrors() {
        binding.titleET.editText!!.doOnTextChanged { _, _, _, _ ->
            binding.titleET.error = null
        }
        binding.descriptionET.editText!!.doOnTextChanged { _, _, _, _ ->
            binding.descriptionET.error = null
        }
    }

    private fun pickDateListener() {
        binding.selectedTime.setOnClickListener {
            chooseDate()
        }
    }

    private fun chooseDate() {
        TODO("Not yet implemented")
    }

    private fun enterData() {
        binding.addBtn.setOnClickListener {
            if (isValid()) {
                //TODO: save data in db
                finish()
            }
        }
    }

    private fun finish() {
        requireActivity().supportFragmentManager.beginTransaction()
            .remove(this)
            .commit()
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

}