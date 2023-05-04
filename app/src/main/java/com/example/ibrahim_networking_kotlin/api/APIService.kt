package com.example.ibrahim_networking_kotlin.api

import com.example.ibrahim_networking_kotlin.ResponseQuotes
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("quotes")
    fun getQuotes() : Call<ResponseQuotes>
}