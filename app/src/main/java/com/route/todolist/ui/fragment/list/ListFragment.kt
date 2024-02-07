package com.route.todolist.ui.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.route.todolist.R
import com.route.todolist.adapter.TasksAdapter
import com.route.todolist.database.data_model.Tasks
import com.route.todolist.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    //    private var adapter = TasksAdapter(listOf(
//        Tasks(1,"title","des","today",false),
//        Tasks(2,"title2","des2","tomorrow",false)
//        ))
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.recyclerView.adapter = adapter
    }

}