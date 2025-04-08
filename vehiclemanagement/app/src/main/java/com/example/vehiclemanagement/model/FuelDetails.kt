package com.example.vehiclemanagement.model

data class FuelDetails(
    val id: Long? = null,
    val vehicleId: String,
    val vehicleType: String,    // ✅ Existing
    val fuelAmount: Double,
    val amountSpent: Double,
    val date: String,
    val userId: String,         // ✅ New field
    val fuelType: String        // ✅ New field
)
