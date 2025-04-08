package com.example.vehiclemanagement.api

import com.example.vehiclemanagement.model.FuelDetails
import com.example.vehiclemanagement.model.InOutTime
import com.example.vehiclemanagement.model.VehicleRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Fuel Details
    @POST("/api/fuel/add")
    fun addFuelDetails(@Body fuelDetails: FuelDetails): Call<ResponseBody>

    @GET("/api/fuel/all")
    fun getFuelDetails(): Call<List<FuelDetails>>

    // In/Out Time
    @POST("/api/inout/add")
    fun addInOutTime(@Body inOutTime: InOutTime): Call<ResponseBody>

    @GET("/api/inout/all")
    fun getInOutTimeDetails(): Call<List<InOutTime>>

    // Vehicle Request
    // Vehicle Request
    @POST("/api/requests/add")
    fun addVehicleRequest(@Body request: VehicleRequest): Call<ResponseBody>

    @GET("/api/requests/all")
    fun getVehicleRequests(): Call<List<VehicleRequest>>

}
