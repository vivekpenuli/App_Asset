package com.example.food_admin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_admin.DataModel.pendingmodel
import com.example.food_admin.databinding.PendingOrderRecyclerviewBinding
class PendingOrderAdapter(
    private val pendingOrders: MutableList<pendingmodel>
) : RecyclerView.Adapter<PendingOrderAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: PendingOrderRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
var isAccepted = false

        fun bind(item: pendingmodel) {
            binding.dishImageView.setImageResource(item.imageResource)
            binding.dishNameTextView.text = item.username
            binding.dishCostTextView.text = item.dishcount.toString()

            binding.orderaccept.apply {
                text = if (!isAccepted) {
                    "Accept"
                } else {
                    "Dispatch"
                }

                setOnClickListener {
                    if (!isAccepted) {
                        // Handle the "Accept" button click here
                        isAccepted = true
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            // Remove the item from the list
                            pendingOrders.removeAt(position)
                            // Notify the adapter about the removal
                            notifyItemRemoved(position)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PendingOrderRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pendingOrder = pendingOrders[position]
        holder.bind(pendingOrder)
    }

    override fun getItemCount(): Int {
        return pendingOrders.size
    }
}

