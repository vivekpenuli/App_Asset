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

//    Add a property for the click listener in your adapter:

    private var itemClickListener: OnItemClickListener? = null

        //Add a function in your adapter to set the click listener from outside:

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    // what is MenuBindindg
    /*
    To determine which XML layout file is associated with a particular generated binding class, you can look at the naming convention of the binding class. The naming convention for a binding class is as follows:

For a layout file named example_layout.xml, the corresponding binding class is named ExampleLayoutBinding.
        */
    
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    //    Modify your ViewHolder class to set an OnClickListener on the root view (the entire item):
    
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

