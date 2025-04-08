package com.example.vehiclemanagement.model

data class InOutTime(
    val vehicleId: String,
    val vehicleType: String,
    val inTime: String,
    val outTime: String,
    val date: String,
    val userId: String
)

