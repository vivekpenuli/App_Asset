package com.example.food_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.food_admin.Adapter.orderDetailsAdapter
import com.example.food_admin.databinding.ActivityPendinOnclickBinding
import com.example.food_admin.databinding.ActivityPendingOrderBinding

class PendinOnclickActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendinOnclickBinding

    private  var userName : String ? = null
    private var phonenumber : String ? = null
    private var totalPrice: String ? = null
    private var foodNames: MutableList<String> = mutableListOf()
    private var foodImages : MutableList<String> = mutableListOf()
    private var foodQuantity : MutableList<Int> = mutableListOf()
    private var foodPrice: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendinOnclickBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        getdataFromIntent()
    }

    private fun getdataFromIntent() {
userName = intent.getStringExtra("username")
        phonenumber = intent.getStringExtra("phone")
        totalPrice = intent.getStringExtra("totalprice")
        val stringListfoodname = intent.getStringExtra("foodNames")
        foodNames = stringListfoodname?.split(",")?.toMutableList()!!

        val stringListfoodimg = intent.getStringExtra("foodimg")
        foodImages = stringListfoodimg?.split(",")?.toMutableList()!!

         foodQuantity= intent.extras?.getIntegerArrayList("foodquan")!!


        val stringListfoodprice = intent.getStringExtra("foodprice")
        foodPrice = stringListfoodprice?.split(",")?.toMutableList()!!


        setuserDetails()
        setAdapter()
    }

    private fun setAdapter() {
        binding.orderrecylcer.layoutManager = LinearLayoutManager(this)
        val adapter = orderDetailsAdapter(this,foodNames,foodImages,foodQuantity,foodPrice)
        binding.orderrecylcer.adapter= adapter
    }

    private fun setuserDetails() {
        binding.ordername.text = userName
        binding.orderphone.text = phonenumber
        binding.orderquant.text = foodQuantity.sum().toString()
binding.orderprice2.text = totalPrice
    }
}
