package com.example.vehiclemanagement

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.adapter.FuelDetailsAdapter
import com.example.vehiclemanagement.api.RetrofitClient
import com.example.vehiclemanagement.model.FuelDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FuelDetailsManagerActivity : AppCompatActivity() {

    private lateinit var recyclerFuelDetails: RecyclerView
    private lateinit var fuelDetailsAdapter: FuelDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_details_manager)

        recyclerFuelDetails = findViewById(R.id.recyclerFuelDetails)
        recyclerFuelDetails.layoutManager = LinearLayoutManager(this)

        fetchFuelDetails()
    }

    private fun fetchFuelDetails() {
        RetrofitClient.api.getFuelDetails().enqueue(object : Callback<List<FuelDetails>> {
            override fun onResponse(
                call: Call<List<FuelDetails>>,
                response: Response<List<FuelDetails>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val fuelList = response.body()!!
                    fuelDetailsAdapter = FuelDetailsAdapter(fuelList)
                    recyclerFuelDetails.adapter = fuelDetailsAdapter
                } else {
                    Toast.makeText(this@FuelDetailsManagerActivity, "No fuel data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FuelDetails>>, t: Throwable) {
                Toast.makeText(this@FuelDetailsManagerActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("FuelFetch", "Failed to fetch fuel data", t)
            }
        })
    }
}
