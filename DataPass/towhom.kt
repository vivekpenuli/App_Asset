package com.example.food_order

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.food_order.DataModel.AddtoFirebase
import com.example.food_order.databinding.ActivityFoodItemDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FoodItemActivity : AppCompatActivity() {
       private lateinit var binding: ActivityFoodItemDetailBinding
    private lateinit var auth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodItemDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Initalize firebase auth
        auth = FirebaseAuth.getInstance()   // calling firebase instance

binding.imageButton.setOnClickListener {

    val intent = Intent(this, MainActivity::class.java)             // calling the new intent and stopping it from calling again
    startActivity(intent)
    finish()
}
/*
When Data from one activity passed to other activit as in Bundle we need to 
Use those data into other activity by calling  intent .
Intent is a way of using the data passed from one activity to be used by otehr activty 
*/
        val foodName = intent.getStringExtra("itemName")
        val foodPrice = intent.getStringExtra("itemprice")
        val foodImg = intent.getStringExtra("imageResourceId")
        val foodDisc = intent.getStringExtra("itemdisc")
        val foodIngre = intent.getStringExtra("itemingre")


        if (foodName != null && foodPrice != null && foodImg != null && foodDisc!=null && foodIngre!=null) {
            binding.food.text = foodName
            binding.price.text = foodPrice
            binding.shortdisc.text =foodDisc
            binding.ingredi.text = foodIngre

            // Load the image using Glide (you should have Glide set up in your project)
            Glide.with(this)
                .load(Uri.parse(foodImg))
                .into(binding.foodimage)
        } else {
            // Handle the case where one or more values are null
            // You can show an error message or take appropriate action
        }

        binding.add.setOnClickListener {
            val database = FirebaseDatabase.getInstance().reference          // calling the firabase database object
            val userId = auth.currentUser?.uid?:""

            val cartItem = AddtoFirebase(foodName,foodPrice,foodDisc,foodImg,foodIngre,1,foodPrice)
            database.child("App_user").child(userId).child("CartItem").push().setValue(cartItem).addOnSuccessListener {
                Toast.makeText(this, "Sucessful inserted", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Not inserted in databse", Toast.LENGTH_SHORT).show()
// whenever we face any failure  during on click a toast will be created
            }

        }

    }
}
