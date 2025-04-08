package com.example.vehiclemanagement

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddDriverActivity : AppCompatActivity() {
    private lateinit var editTextDriverName: EditText
    private lateinit var editTextDriverLicense: EditText
    private lateinit var btnSubmitDriver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_driver)

        editTextDriverName = findViewById(R.id.editTextDriverName)
        editTextDriverLicense = findViewById(R.id.editTextDriverLicense)
        btnSubmitDriver = findViewById(R.id.btnSubmitDriver)

        btnSubmitDriver.setOnClickListener {
            val driverName = editTextDriverName.text.toString().trim()
            val driverLicense = editTextDriverLicense.text.toString().trim()

            if (driverName.isEmpty() || driverLicense.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Driver Added Successfully!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
