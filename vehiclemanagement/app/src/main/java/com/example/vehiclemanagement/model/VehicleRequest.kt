package com.example.vehiclemanagement.model

data class VehicleRequest(
    val userId: Int,
    val vehicleNumber: String,
    val vehicleType: String,
    val purpose: String,
    val requestDate: String
)
