package com.example.ibrahim_networking_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.ibrahim_networking_kotlin.SettingPreferences

class ViewModelFactory(private val pref: SettingPreferences) : NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((ThemeViewModel::class.java))) {
            return ThemeViewModel(pref) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}