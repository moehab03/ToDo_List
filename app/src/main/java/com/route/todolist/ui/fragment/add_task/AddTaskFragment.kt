package com.route.todolist.ui.fragment.add_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todolist.R
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
        pickDateListener()
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
        if (binding.titleEditText.editText!!.text.isEmpty()) {
            binding.titleEditText.error = "Please enter title"
            isValid = false
        }
        if (binding.descriptionEditText.editText!!.text.isEmpty()) {
            binding.descriptionEditText.error = "Please enter description"
            isValid = false
        }
        return isValid
    }

}