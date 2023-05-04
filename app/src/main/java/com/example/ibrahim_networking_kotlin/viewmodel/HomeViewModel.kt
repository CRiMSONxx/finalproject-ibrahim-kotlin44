package com.example.ibrahim_networking_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ibrahim_networking_kotlin.ResultsItem


class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var posts: MutableLiveData<List<ResultsItem>> = MutableLiveData<List<ResultsItem>>()
}