package com.route.todolist.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.route.todolist.R
import com.route.todolist.databinding.ActivityHomeBinding
import com.route.todolist.ui.fragment.add_task.AddTaskFragment
import com.route.todolist.ui.fragment.list.ListFragment
import com.route.todolist.ui.fragment.settings.SettingsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val listFragment = ListFragment()
    private val settingsFragment = SettingsFragment()
    private val addTaskFragment = AddTaskFragment {
        listFragment.refreshTasksList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(listFragment)
        onMenuClick()
        onAddClick()
    }

    private fun onMenuClick() {
        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.menu_list_icon -> {
                    showFragment(listFragment)
                    changeAppBarText("To Do List")
                }

                R.id.menu_settings_icon -> {
                    showFragment(settingsFragment)
                    changeAppBarText("Settings")
                }
            }
            true
        }
    }

    private fun changeAppBarText(title: String) {
        binding.title.text = title
    }

    private fun onAddClick() {
        binding.fab.setOnClickListener {
            addTaskFragment.show(supportFragmentManager, "add_fragment")
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}