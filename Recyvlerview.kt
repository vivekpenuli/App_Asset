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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(private val binding: CartitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: cartdata) {
            binding.dishImageView.setImageResource(item.imageResource)
            binding.dishNameTextView.text = item.dishname
            binding.dishCostTextView.text = item.dishprice
            binding.itemCountTextView.text = item.dishcount.toString()

        }
    }
}




