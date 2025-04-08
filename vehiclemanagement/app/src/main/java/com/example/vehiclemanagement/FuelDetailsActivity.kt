package com.example.vehiclemanagement

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclemanagement.api.RetrofitClient
import com.example.vehiclemanagement.model.FuelDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FuelDetailsActivity : AppCompatActivity() {

    private lateinit var editTextVehicleId: EditText
    private lateinit var editTextVehicleType: EditText
    private lateinit var editTextFuelAmount: EditText
    private lateinit var editTextAmountSpent: EditText
    private lateinit var editTextFuelDate: EditText
    private lateinit var editTextUserId: EditText             // ✅ NEW
    private lateinit var editTextFuelType: EditText           // ✅ NEW
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_details)

        // Initialize UI components
        editTextVehicleId = findViewById(R.id.editTextVehicleId)
        editTextVehicleType = findViewById(R.id.editTextVehicleType)
        editTextFuelAmount = findViewById(R.id.editTextFuelAmount)
        editTextAmountSpent = findViewById(R.id.editTextAmountSpent)
        editTextFuelDate = findViewById(R.id.editTextFuelDate)
        editTextUserId = findViewById(R.id.editTextUserId)             // ✅
        editTextFuelType = findViewById(R.id.editTextFuelType)         // ✅
        btnSubmit = findViewById(R.id.btnSubmit)

        // Show date picker when user clicks date field
        editTextFuelDate.setOnClickListener { showDatePicker() }

        // Submit button click
        btnSubmit.setOnClickListener { submitFuelDetails() }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                editTextFuelDate.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun submitFuelDetails() {
        val vehicleId = editTextVehicleId.text.toString().trim()
        val vehicleType = editTextVehicleType.text.toString().trim()
        val fuelAmountText = editTextFuelAmount.text.toString().trim()
        val amountSpentText = editTextAmountSpent.text.toString().trim()
        val fuelDate = editTextFuelDate.text.toString().trim()
        val userId = editTextUserId.text.toString().trim()         // ✅
        val fuelType = editTextFuelType.text.toString().trim()     // ✅

        if (vehicleId.isEmpty() || vehicleType.isEmpty() || fuelAmountText.isEmpty() ||
            amountSpentText.isEmpty() || fuelDate.isEmpty() || userId.isEmpty() || fuelType.isEmpty()
        ) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val fuelAmount = fuelAmountText.toDoubleOrNull()
        val amountSpent = amountSpentText.toDoubleOrNull()

        if (fuelAmount == null || amountSpent == null) {
            Toast.makeText(this, "Enter valid numeric values", Toast.LENGTH_SHORT).show()
            return
        }

        val fuelDetails = FuelDetails(
            vehicleId = vehicleId,
            vehicleType = vehicleType,
            fuelAmount = fuelAmount,
            amountSpent = amountSpent,
            date = fuelDate,
            userId = userId,                   // ✅ NEW
            fuelType = fuelType                // ✅ NEW
        )

        RetrofitClient.api.addFuelDetails(fuelDetails).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@FuelDetailsActivity,
                        "Fuel details submitted!",
                        Toast.LENGTH_LONG
                    ).show()
                    clearFields()
                } else {
                    Toast.makeText(
                        this@FuelDetailsActivity,
                        "Submission failed: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@FuelDetailsActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun clearFields() {
        editTextVehicleId.text.clear()
        editTextVehicleType.text.clear()
        editTextFuelAmount.text.clear()
        editTextAmountSpent.text.clear()
        editTextFuelDate.text.clear()
        editTextUserId.text.clear()            // ✅
        editTextFuelType.text.clear()          // ✅
    }
}
