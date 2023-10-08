package com.example.food_order.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_order.DataModel.YourDataModel
import com.example.food_order.databinding.MenuitemBinding

class MenuAdapter(private val dataSet: List<YourDataModel>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

//         To open a new activity when you click on a RecyclerView item, you need to add a click listener to the items in your MenuAdapter. Here's how you can do that:
// First, create an interface in your adapter to define a click listener:
    interface OnItemClickListener {
        fun onItemClick(item: YourDataModel)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(private val binding: MenuitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: YourDataModel) {
            binding.textViewMiddle.text = item.foodName
            binding.textViewFirst.text = item.foodPrice
            val uriString: String = item.foodImg.toString()
            val uri: Uri = Uri.parse(uriString)
            Glide.with(binding.root.context)
                .load(uri)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(item)
            }
        }
    }
}

