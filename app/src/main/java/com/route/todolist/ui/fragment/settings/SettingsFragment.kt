package com.route.todolist.ui.fragment.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.route.todolist.R
import com.route.todolist.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLanguageSpinner()
        initModeSpinner()
    }

    private fun initModeSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireActivity(), R.array.modes_array, R.layout.spinner_layout
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
        binding.modeSpinner.adapter = arrayAdapter
        binding.modeSpinner.onItemSelectedListener = this //this to display selected item
    }

    private fun initLanguageSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireActivity(), R.array.language_array, R.layout.spinner_layout
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
        binding.languageSpinner.adapter = arrayAdapter
        binding.languageSpinner.onItemSelectedListener = this //this to display selected item
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            Toast.makeText(
                requireContext(),
                parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}