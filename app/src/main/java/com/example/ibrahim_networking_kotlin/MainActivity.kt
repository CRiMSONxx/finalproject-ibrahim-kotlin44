package com.example.ibrahim_networking_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibrahim_networking_kotlin.databinding.ActivityMainBinding
import com.example.ibrahim_networking_kotlin.viewmodel.ThemeViewModel
import com.example.ibrahim_networking_kotlin.viewmodel.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var themeViewModel: ThemeViewModel

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())
        applyTheme()

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                }

                R.id.menu_dashboard -> {
                    loadFragment(ListQuoteFragment())
                }

                R.id.menu_profile -> {
                    //loadFragment(SettingFragment())
                    val intent = Intent(this@MainActivity, ThemeActivity::class.java)
                    startActivity(intent)
                }

            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.theme_menu -> {
                val intent = Intent(this@MainActivity, ThemeActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun applyTheme() {
        val pref = SettingPreferences.getInstance(dataStore)
        themeViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[ThemeViewModel::class.java]

        themeViewModel.getTheme().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}