import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.food_order.DataModel.AddtoFirebase
import com.example.food_order.databinding.CartitemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Replace with your actual package name
class CartBindingAdapter(private val dataSet: MutableList<AddtoFirebase>) :
    RecyclerView.Adapter<CartBindingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(private val binding: CartitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddtoFirebase) {
            binding.dishNameTextView.text = item.foodName
//            binding.dishCostTextView.text = item.foodPrice   // The reasin i dont use this is beacuse i need to use the price value from quantity
            updateTotalPrice(item)
            /*
            what i want is when my recylerview open the foodprice should be based on quantity i choose 
            food quantity is updated in firebase but new food price is not 
            so , instead of   binding.dishCostTextView.text = item.foodPrice whant i do is call the function which
            will find the foodprice based on quantity .
            */
            binding.itemCountTextView.text = item.fooddQuantity.toString()

            // Handle click on "Add" button
            binding.addButton.setOnClickListener {
                item.fooddQuantity = item.fooddQuantity?.plus(1)
                updateTotalPrice(item)
                updateQuantityInFirebase(item)

            }

            // Handle click on "Delete" button
            binding.removeButton.setOnClickListener {
                if (item.fooddQuantity!! > 1) {
                    item.fooddQuantity = item.fooddQuantity!! - 1
                    updateTotalPrice(item)

                    // Update the quantity in Firebase
                    updateQuantityInFirebase(item)  // When the food Quantity increase i am calling fireabase to increase the quantity of item in firebase
                }
            }

            val uriString: String = item.foodImg.toString()
            val uri: Uri = Uri.parse(uriString)
            Glide.with(binding.root.context)
                .load(uri)
                .into(binding.dishImageView)


        }
        private fun updateQuantityInFirebase(item: AddtoFirebase) {

            // Inside your CartBindingAdapter

                val database = FirebaseDatabase.getInstance()
                val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

                // Replace 'foodId' with the actual identifier for the item
                val foodId = item.foodId  // Since each food item is assigned a specific Id , with the help of that Id we are able to find which food item quantity count to increase in fireabse

                val orderReference: DatabaseReference? =
                    foodId?.let {
                        database.reference.child("App_user").child(userId).child("CartItem").child(
                            it
                        )
                    }

                // Update the quantity in Firebase
            orderReference?.child("fooddQuantity")?.setValue(item.fooddQuantity)
/*
Once the item qunatity increse we need to push the new quantity to firebase 
*/

        }
        // Function to update the total price when quantity changes
        private fun updateTotalPrice(item: AddtoFirebase) {
            val currentPrice = item.baseprice?.toInt() ?: 0
            item.foodPrice = (currentPrice * item.fooddQuantity!!).toString()
            binding.dishCostTextView.text = item.foodPrice
            binding.itemCountTextView.text = item.fooddQuantity.toString()
        }
    }
}

