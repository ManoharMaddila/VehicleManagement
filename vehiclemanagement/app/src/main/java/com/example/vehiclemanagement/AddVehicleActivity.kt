package com.example.vehiclemanagement

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddVehicleActivity : AppCompatActivity() {
    private lateinit var editTextVehicleName: EditText
    private lateinit var editTextVehicleModel: EditText
    private lateinit var editTextVehicleNumber: EditText
    private lateinit var btnSubmitVehicle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)

        editTextVehicleName = findViewById(R.id.editTextVehicleName)
        editTextVehicleModel = findViewById(R.id.editTextVehicleModel)
        editTextVehicleNumber = findViewById(R.id.editTextVehicleNumber)
        btnSubmitVehicle = findViewById(R.id.btnSubmitVehicle)

        btnSubmitVehicle.setOnClickListener {
            val vehicleName = editTextVehicleName.text.toString().trim()
            val vehicleModel = editTextVehicleModel.text.toString().trim()
            val vehicleNumber = editTextVehicleNumber.text.toString().trim()

            if (vehicleName.isEmpty() || vehicleModel.isEmpty() || vehicleNumber.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Send data to the backend
                Toast.makeText(this, "Vehicle Added Successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
