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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(ListFragment())
        onMenuClick()
        onAddClick()
    }

    private fun onMenuClick() {
        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.menu_list_icon -> showFragment(ListFragment())
                R.id.menu_settings_icon -> showFragment(SettingsFragment())
            }

            true
        }
    }

    private fun onAddClick() {
        binding.fab.setOnClickListener {
            AddTaskFragment().show(supportFragmentManager, "")
        }
    }

    private fun showFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()

    }
}