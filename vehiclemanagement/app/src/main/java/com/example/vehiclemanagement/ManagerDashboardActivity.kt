package com.example.vehiclemanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclemanagement.api.RetrofitClient
import com.example.vehiclemanagement.model.FuelDetails
import com.example.vehiclemanagement.model.InOutTime
import com.example.vehiclemanagement.model.VehicleRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagerDashboardActivity : AppCompatActivity() {

    private lateinit var btnViewFuelDetails: Button
    private lateinit var btnViewInOutTimes: Button
    private lateinit var btnViewVehicleRequests: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_dashboard)

        // Initialize buttons
        btnViewFuelDetails = findViewById(R.id.btnViewFuelDetails)
        btnViewInOutTimes = findViewById(R.id.btnViewInOutTimes)
        btnViewVehicleRequests = findViewById(R.id.btnViewVehicleRequests)

        // Set up click listeners
        btnViewFuelDetails.setOnClickListener { checkFuelDetails() }
        btnViewInOutTimes.setOnClickListener { checkInOutTimes() }
        btnViewVehicleRequests.setOnClickListener { checkVehicleRequests() }
    }

    private fun checkFuelDetails() {
        RetrofitClient.api.getFuelDetails().enqueue(object : Callback<List<FuelDetails>> {
            override fun onResponse(call: Call<List<FuelDetails>>, response: Response<List<FuelDetails>>) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    startActivity(Intent(this@ManagerDashboardActivity, FuelDetailsManagerActivity::class.java))
                } else {
                    showToast("No Fuel Details available")
                }
            }

            override fun onFailure(call: Call<List<FuelDetails>>, t: Throwable) {
                showToast("Error fetching Fuel Details")
                Log.e("ManagerDashboard", "FuelDetails fetch error", t)
            }
        })
    }

    private fun checkInOutTimes() {
        RetrofitClient.api.getInOutTimeDetails().enqueue(object : Callback<List<InOutTime>> {
            override fun onResponse(call: Call<List<InOutTime>>, response: Response<List<InOutTime>>) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    // ✅ Redirect to Manager's In/Out Time view
                    startActivity(Intent(this@ManagerDashboardActivity, InOutTimeManagerActivity::class.java))
                } else {
                    showToast("No In/Out Time details available")
                }
            }

            override fun onFailure(call: Call<List<InOutTime>>, t: Throwable) {
                showToast("Error fetching In/Out Times")
                Log.e("ManagerDashboard", "InOutTime fetch error", t)
            }
        })
    }

    private fun checkVehicleRequests() {
        RetrofitClient.api.getVehicleRequests().enqueue(object : Callback<List<VehicleRequest>> {
            override fun onResponse(call: Call<List<VehicleRequest>>, response: Response<List<VehicleRequest>>) {
                if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                    startActivity(Intent(this@ManagerDashboardActivity, RequestVehicleManagerActivity::class.java))
                } else {
                    showToast("No Vehicle Requests available")
                }
            }

            override fun onFailure(call: Call<List<VehicleRequest>>, t: Throwable) {
                showToast("Error fetching Vehicle Requests")
                Log.e("ManagerDashboard", "VehicleRequest fetch error", t)
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
