package com.example.vehiclemanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.R
import com.example.vehiclemanagement.model.InOutTime

class InOutTimeAdapter(private val inOutList: List<InOutTime>) :
    RecyclerView.Adapter<InOutTimeAdapter.InOutViewHolder>() {

    class InOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vehicleId: TextView = itemView.findViewById(R.id.txtVehicleId)
        val vehicleType: TextView = itemView.findViewById(R.id.txtVehicleType)
        val inTime: TextView = itemView.findViewById(R.id.txtInTime)
        val outTime: TextView = itemView.findViewById(R.id.txtOutTime)
        val date: TextView = itemView.findViewById(R.id.txtDate)
        val userId: TextView = itemView.findViewById(R.id.txtUserId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InOutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_in_out_time, parent, false)
        return InOutViewHolder(view)
    }

    override fun onBindViewHolder(holder: InOutViewHolder, position: Int) {
        val inOut = inOutList[position]
        holder.vehicleId.text = "Vehicle ID: ${inOut.vehicleId}"
        holder.vehicleType.text = "Type: ${inOut.vehicleType}"
        holder.inTime.text = "In Time: ${inOut.inTime}"
        holder.outTime.text = "Out Time: ${inOut.outTime}"
        holder.date.text = "Date: ${inOut.date}"
        holder.userId.text = "User ID: ${inOut.userId}"
    }

    override fun getItemCount(): Int = inOutList.size
}
