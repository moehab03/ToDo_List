package com.route.todolist.ui.fragment.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todolist.Constant
import com.route.todolist.adapter.TasksAdapter
import com.route.todolist.database.database.TasksDatabase
import com.route.todolist.databinding.FragmentListBinding
import com.route.todolist.timeInMillis
import com.route.todolist.ui.update.UpdateActivity
import java.util.Calendar

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private var date = Calendar.getInstance()
    private var selectedDate = CalendarDay.today()
    private var adapter = TasksAdapter(
        listOf(),
        { TasksDatabase.getInstance(requireContext()).tasksDao().update(it) },
        {
            if (it.isDone == false) {
                val intent = Intent(activity, UpdateActivity::class.java)
                intent.putExtra(Constant.TASK, it)
                startActivity(intent)
            }
        }
    )

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
        binding.recyclerView.adapter = adapter
        initCalender()
        refreshTasksList()
    }

    override fun onResume() {
        super.onResume()
        refreshTasksList()
    }

    private fun initCalender() {
        with(binding) {
            calendarView.selectedDate = selectedDate
            calendarView.setOnDateChangedListener { _, date, _ ->
                selectedDate = date
                refreshTasksList()
            }
        }
    }

    fun refreshTasksList() {
        adapter.updateTasks(
            TasksDatabase.getInstance(requireContext()).tasksDao()
                .getTasksByDate(selectedDate.timeInMillis())
        )
    }
}