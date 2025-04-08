package com.example.vehiclemanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Find Views
        val etEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)


        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Dummy Check (Replace with backend validation)
            if (email == "manager@example.com" && password == "manager123") {
                Toast.makeText(this, "Manager Logged in Successfully!", Toast.LENGTH_SHORT).show()

                // Redirect to Manager Dashboard
                val intent = Intent(this, ManagerDashboardActivity::class.java)
                startActivity(intent)
                finish() // Close Login Activity
            } else if (email == "user@example.com" && password == "user123") {
                Toast.makeText(this, "User Logged in Successfully!", Toast.LENGTH_SHORT).show()

                // Redirect to User Dashboard
                val intent = Intent(this, UserDashboardActivity::class.java)
                startActivity(intent)
                finish() // Close Login Activity
            } else {
                Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
