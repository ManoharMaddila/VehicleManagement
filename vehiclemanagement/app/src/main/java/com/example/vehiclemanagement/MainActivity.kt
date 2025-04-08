package com.example.vehiclemanagement

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the Action Bar (Removes Title on Splash Screen)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)

        // Show the splash screen for 4 seconds before moving to LoginActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close this activity so the user can't go back to it
        }, 4000)
    }
}
