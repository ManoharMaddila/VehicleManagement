package com.example.vehiclemanagement

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclemanagement.api.RetrofitClient
import com.example.vehiclemanagement.model.InOutTime
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class InOutTimeActivity : AppCompatActivity() {

    private lateinit var editTextVehicleId: EditText
    private lateinit var editTextVehicleType: EditText
    private lateinit var editTextInTime: EditText
    private lateinit var editTextOutTime: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextUserId: EditText
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_out_time)

        // Initialize views
        editTextVehicleId = findViewById(R.id.editTextVehicleId)
        editTextVehicleType = findViewById(R.id.editTextVehicleType)
        editTextInTime = findViewById(R.id.editTextInTime)
        editTextOutTime = findViewById(R.id.editTextOutTime)
        editTextDate = findViewById(R.id.editTextDate)
        editTextUserId = findViewById(R.id.editTextUserId)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Show date picker dialog
        editTextDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    editTextDate.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Submit button click
        btnSubmit.setOnClickListener {
            submitInOutTime()
        }
    }

    private fun submitInOutTime() {
        val vehicleId = editTextVehicleId.text.toString()
        val vehicleType = editTextVehicleType.text.toString()
        val inTime = editTextInTime.text.toString()
        val outTime = editTextOutTime.text.toString()
        val date = editTextDate.text.toString()
        val userId = editTextUserId.text.toString()

        if (vehicleId.isEmpty() || vehicleType.isEmpty() || inTime.isEmpty() || outTime.isEmpty() || date.isEmpty() || userId.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inOutTime = InOutTime(vehicleId, vehicleType, inTime, outTime, date, userId)

        val call = RetrofitClient.api.addInOutTime(inOutTime)
        call.enqueue(object : Callback<okhttp3.ResponseBody> {
            override fun onResponse(call: Call<okhttp3.ResponseBody>, response: Response<okhttp3.ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@InOutTimeActivity, "In/Out Time submitted!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@InOutTimeActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {
                Toast.makeText(this@InOutTimeActivity, "Submission failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
