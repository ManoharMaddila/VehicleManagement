package com.example.vehiclemanagement

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclemanagement.api.RetrofitClient
import com.example.vehiclemanagement.model.VehicleRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleRequestActivity : AppCompatActivity() {

    private lateinit var editTextUserId: EditText
    private lateinit var editTextVehicleNumber: EditText
    private lateinit var editTextVehicleType: EditText
    private lateinit var editTextPurpose: EditText
    private lateinit var editTextRequestDate: EditText
    private lateinit var btnRequest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_vehicle)

        // Initialize UI elements
        editTextUserId = findViewById(R.id.editTextUserId)
        editTextVehicleNumber = findViewById(R.id.editTextVehicleNumber)
        editTextVehicleType = findViewById(R.id.editTextVehicleType)
        editTextPurpose = findViewById(R.id.editTextReason)
        editTextRequestDate = findViewById(R.id.editTextRequestDate)
        btnRequest = findViewById(R.id.btnRequest)

        // Set button click listener
        btnRequest.setOnClickListener {
            submitVehicleRequest()
        }
    }

    private fun submitVehicleRequest() {
        val userIdStr = editTextUserId.text.toString().trim()
        val vehicleNumber = editTextVehicleNumber.text.toString().trim()
        val vehicleType = editTextVehicleType.text.toString().trim()
        val purpose = editTextPurpose.text.toString().trim()
        val requestDate = editTextRequestDate.text.toString().trim()

        if (userIdStr.isEmpty() || vehicleNumber.isEmpty() || vehicleType.isEmpty() || purpose.isEmpty() || requestDate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = try {
            userIdStr.toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "User ID must be a number", Toast.LENGTH_SHORT).show()
            return
        }

        val request = VehicleRequest(userId, vehicleNumber, vehicleType, purpose, requestDate)

        RetrofitClient.api.addVehicleRequest(request)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@VehicleRequestActivity, "Vehicle Request Sent", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@VehicleRequestActivity, "Failed to send request", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@VehicleRequestActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
