package com.example.vehiclemanagement

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class RequestVehicleManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_vehicle_manager)

        // Reference to ListView
        val listViewRequests: ListView = findViewById(R.id.listViewRequests)

        // Fetch vehicle requests and display
        fetchVehicleRequestDetails(listViewRequests)
    }

    private fun fetchVehicleRequestDetails(listView: ListView) {
        // Simulating data fetched from backend
        val requestDetailsList = listOf(
            "Vehicle: SUV | Date: 2025-04-01 | Status: Pending",
            "Vehicle: Sedan | Date: 2025-03-30 | Status: Approved",
            "Vehicle: Truck | Date: 2025-03-28 | Status: Rejected"
        )

        // Set up ArrayAdapter to display requests
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, requestDetailsList)
        listView.adapter = adapter
    }
}
