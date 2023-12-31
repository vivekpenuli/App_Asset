import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.food_admin.DataModel.cartdata
import com.example.food_admin.databinding.CartitemBinding


// Replace with your actual package name  
// Note: The file name should be the same as of recylecer view calss name

class CartBindingAdapter(private val dataSet: List<cartdata>) :           // List<cardata> : cardata is the type of data which my list will have
    RecyclerView.Adapter<CartBindingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
/*
        CartitemBinding : ye hamara wo xml he jo ham ne design keya tha recylerview me inflate kar ne ke leye
        nOTE: ye reclerview wali xml file nhai he
        */

    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    // restrict the size of element appearain in recylcerview
    // We can customize acoording to our need how many element do we want in recylerview
    override fun getItemCount() = dataSet.size


        
    class ViewHolder(private val binding: CartitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            // here we are traversing the list we createed of cart data type and aceesing each element through it
        fun bind(item: cartdata) {      // note define the datatype of your data class

            // element whose id we define in XML 
            binding.dishImageView.setImageResource(item.imageResource)
            binding.dishNameTextView.text = item.dishname
            binding.dishCostTextView.text = item.dishprice
            binding.itemCountTextView.text = item.dishcount.toString()

        }
    }
}




