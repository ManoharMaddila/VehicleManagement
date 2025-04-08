package com.example.vehiclemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.R
import com.example.vehiclemanagement.model.VehicleRequest

class VehicleRequestAdapter(private val requestList: List<VehicleRequest>) :
    RecyclerView.Adapter<VehicleRequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicleNumber: TextView = itemView.findViewById(R.id.txtVehicleNumber)
        val requestStatus: TextView = itemView.findViewById(R.id.txtRequestStatus)
        val btnApprove: Button = itemView.findViewById(R.id.btnApprove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_request_vehicle_manager, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requestList[position]
        holder.vehicleNumber.text = "Vehicle: ${request.vehicleNumber}"


        // Handle approval button click
        holder.btnApprove.setOnClickListener {
            // Logic to approve request (update backend API)
        }
    }

    override fun getItemCount(): Int = requestList.size
}
