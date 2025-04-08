package com.example.vehiclemanagement

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.adapter.InOutTimeAdapter
import com.example.vehiclemanagement.api.RetrofitClient
import com.example.vehiclemanagement.model.InOutTime
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InOutTimeManagerActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var inOutAdapter: InOutTimeAdapter
    private var inOutList = ArrayList<InOutTime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_out_time_manager)

        recyclerView = findViewById(R.id.recyclerViewInOutTime)
        recyclerView.layoutManager = LinearLayoutManager(this)
        inOutAdapter = InOutTimeAdapter(inOutList)
        recyclerView.adapter = inOutAdapter

        fetchInOutTimeDetails()
    }

    private fun fetchInOutTimeDetails() {
        RetrofitClient.api.getInOutTimeDetails().enqueue(object : Callback<List<InOutTime>> {
            override fun onResponse(
                call: Call<List<InOutTime>>,
                response: Response<List<InOutTime>>
            ) {
                if (response.isSuccessful) {
                    inOutList.clear()
                    response.body()?.let { inOutList.addAll(it) }
                    inOutAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@InOutTimeManagerActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<InOutTime>>, t: Throwable) {
                Toast.makeText(this@InOutTimeManagerActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("InOutTimeError", t.stackTraceToString())
            }
        })
    }
}
