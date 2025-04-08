package com.example.vehiclemanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Action Bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_register_user)

        // Find Views
        val etFullName = findViewById<EditText>(R.id.etUserFullName)
        val etEmail = findViewById<EditText>(R.id.etUserEmail)
        val etPassword = findViewById<EditText>(R.id.etUserPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etUserConfirmPassword)
        val btnRegister = findViewById<Button>(R.id.btnUserRegister)

        // Handle Register Button Click
        btnRegister.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Validation Checks
            when {
                fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
                password.length < 6 -> {
                    Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Registration Success
                    Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()

                    // Redirect to Login Page
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish() // Close Register Page
                }
            }
        }
    }
}
