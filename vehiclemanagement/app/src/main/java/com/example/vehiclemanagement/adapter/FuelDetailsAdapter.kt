package com.example.vehiclemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.R
import com.example.vehiclemanagement.model.FuelDetails

class FuelDetailsAdapter(private val fuelList: List<FuelDetails>) :
    RecyclerView.Adapter<FuelDetailsAdapter.FuelViewHolder>() {

    class FuelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicleNumber: TextView = itemView.findViewById(R.id.txtVehicleNumber)
        val vehicleType: TextView = itemView.findViewById(R.id.txtVehicleType)
        val fuelAmount: TextView = itemView.findViewById(R.id.txtFuelAmount)
        val amountSpent: TextView = itemView.findViewById(R.id.txtAmountSpent)
        val fuelType: TextView = itemView.findViewById(R.id.txtFuelType)
        val date: TextView = itemView.findViewById(R.id.txtDate)
        val userId: TextView = itemView.findViewById(R.id.txtUserId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fuel_details, parent, false)
        return FuelViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuelViewHolder, position: Int) {
        val fuel = fuelList[position]
        holder.vehicleNumber.text = "Vehicle ID: ${fuel.vehicleId}"
        holder.vehicleType.text = "Vehicle Type: ${fuel.vehicleType}"
        holder.fuelAmount.text = "Fuel: ${fuel.fuelAmount} L"
        holder.amountSpent.text = "Spent: ₹${fuel.amountSpent}"
        holder.fuelType.text = "Fuel Type: ${fuel.fuelType}"
        holder.date.text = "Date: ${fuel.date}"
        holder.userId.text = "User ID: ${fuel.userId}"
    }

    override fun getItemCount(): Int = fuelList.size
}
