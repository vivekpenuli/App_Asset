package com.example.food_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_admin.Adapter.DeliveryAdapter
import com.example.food_admin.DataModel.DeliveryItem
import com.example.food_admin.DataModel.OrderDetials
import com.example.food_admin.DataModel.RecentlypurchaseAdapter
import com.example.food_admin.DataModel.pendingmodel
import com.example.food_admin.databinding.ActivityOutputBinding
import com.example.food_admin.databinding.ActivityPendingOrderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class PendingOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendingOrderBinding
    private  lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userId: String
    val recentlybuyitemlist:ArrayList<OrderDetials> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        binding.imageButton.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        Rretreiveitem()




    }


    private fun Rretreiveitem() {

        // Initalizing authentication varubale

        // iNITITALIZING DATABASE VARIABLE
        database = FirebaseDatabase.getInstance()
val foodref= database.reference.child("OrderDetails")
        foodref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recentlybuyitemlist.clear()
                for (foodsnapshot in snapshot.children) {
                    val menuItemData: OrderDetials? = foodsnapshot.getValue(OrderDetials::class.java)
                    if (menuItemData != null) {
                        recentlybuyitemlist.add(menuItemData)
                    }
                }
                setupRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.pendingrecylcerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecentlypurchaseAdapter(recentlybuyitemlist)
        recyclerView.adapter = adapter

        adapter.setOnAcceptButtonClickListener(object : RecentlypurchaseAdapter.OnAcceptButtonClickListener {
            override fun onAcceptButtonClick(item: OrderDetials) {
                // Handle the "Accept" button click for the specific item
                // For example, you can open a new activity or perform any desired action
                val intent = Intent(this@PendingOrderActivity, PendinOnclickActivity::class.java)
                val bundle = Bundle()
                bundle.putString("username",item.userName)

                val stringList = item.foodNames?.joinToString(",")
                bundle.putString("foodNames", stringList)

                val stringListimage = item.foodImages?.joinToString(",")
                bundle.putString("foodimg",stringListimage)

                bundle.putIntegerArrayList("foodquan",
                    item.foodQuantites?.let { ArrayList(it) })

                bundle.putString("phone",item.phoneNumber)

                val stringListprice = item.foodPrices?.joinToString(",")
                bundle.putString("foodprice",stringListprice)
                // Attach the Bundle to the Intent

                bundle.putString("totalprice",item.totalPrices)
                // Attach the Bundle to the Intent
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

    }

}
