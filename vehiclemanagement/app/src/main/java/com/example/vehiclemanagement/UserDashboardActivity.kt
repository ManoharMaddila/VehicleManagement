package com.example.vehiclemanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserDashboardActivity : AppCompatActivity() {
    // Initialize the buttons
    lateinit var btnAddVehicleTime: Button
    lateinit var btnAddFuelDetails: Button
    lateinit var btnMakeVehicleRequest: Button
    lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard) // Use your layout file

        // Initialize the buttons by using findViewById
        btnAddVehicleTime = findViewById(R.id.btnAddVehicleTime)
        btnAddFuelDetails = findViewById(R.id.btnAddFuelDetails)
        btnMakeVehicleRequest = findViewById(R.id.btnMakeVehicleRequest)
        btnLogout = findViewById(R.id.btnLogout)

        // Navigate to Add In/Out Time Page
        btnAddVehicleTime.setOnClickListener {
            Toast.makeText(this@UserDashboardActivity, "Opening In/Out Time Page", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UserDashboardActivity, InOutTimeActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Add Fuel Details Page
        btnAddFuelDetails.setOnClickListener {
            Toast.makeText(this@UserDashboardActivity, "Opening Fuel Details Page", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UserDashboardActivity, FuelDetailsActivity::class.java)
            startActivity(intent)
        }

        // Navigate to Request Vehicle Page
        btnMakeVehicleRequest.setOnClickListener {
            Toast.makeText(this@UserDashboardActivity, "Opening Request Vehicle Page", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UserDashboardActivity, VehicleRequestActivity::class.java)
            startActivity(intent)
        }

        // Logout functionality (redirect to login page)
        btnLogout.setOnClickListener {
            Toast.makeText(this@UserDashboardActivity, "Logging out...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UserDashboardActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
