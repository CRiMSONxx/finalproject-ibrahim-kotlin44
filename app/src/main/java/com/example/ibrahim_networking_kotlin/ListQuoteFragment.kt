package com.example.ibrahim_networking_kotlin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ibrahim_networking_kotlin.api.APIConfig
import com.example.ibrahim_networking_kotlin.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListQuoteFragment : Fragment() {

    companion object {
        fun newInstance() = ListQuoteFragment()
    }

    private lateinit var viewModel: ListQuoteViewModel
    private val resultQuote = MutableLiveData<ArrayList<AdapterCat>?>()
    class ListViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvAuthor: TextView = view.findViewById<TextView>(R.id.tv_breed_fill)
        val tvContent: TextView = view.findViewById<TextView>(R.id.tv_country_fill)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tempel = inflater.inflate(R.layout.fragment_list_quote, container, false);
        APIConfig.getService()
            .getQuotes()
            .enqueue(object : Callback<ResponseQuotes> {
                override fun onResponse(
                    call: Call<ResponseQuotes>,
                    response: Response<ResponseQuotes>
                ) {
                    if (response.isSuccessful) {
                        //listUsers.postValue(response.body()?.results)
                        Log.d("tes ", response.body().toString())
                        //Toast.makeText(view!!.context, "Loading berhasil", Toast.LENGTH_LONG).show()

                        val ResponseQuotes = response.body()
                        val dataItem = ResponseQuotes?.results
                        val adapterCat = AdapterCat(dataItem)
                        Log.d("tes : ", response.body().toString())
                        tempel.findViewById<RecyclerView>(R.id.rv_cat).apply {
                            layoutManager = LinearLayoutManager(getActivity())
                            setHasFixedSize(true)
                            adapterCat.notifyDataSetChanged()
                            adapter = adapterCat
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseQuotes>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                    //Toast.makeText(getActivity(), "Data not Found", Toast.LENGTH_LONG).show()
                }
            })
        return tempel
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListQuoteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}