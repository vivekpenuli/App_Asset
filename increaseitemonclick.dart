  // Handle click on "Add" button
            binding.addButton.setOnClickListener {
                item.fooddQuantity = item.fooddQuantity!! + 1

                val currentPrice = item.foodPrice?.toIntOrNull() ?: 0
                item.foodPrice = (currentPrice + (item.baseprice?.toInt() ?: 0)).toString()
                  /* baseprice help to retian the actutal price even when the UI update .
                   Becuse what happen is if i do like this,
                    val currentPrice = item.foodPrice?.toIntOrNull() ?: 0
                 item.foodPrice = (currentPrice + (item.foodprice?.toInt() ?: 0)).toString()
                 here we do item.foodprice added to our current price. But what happen is when UI updated itemprice also updated
                  */
                println("base price ${item.baseprice?.toInt() ?: 0}")
                binding.dishCostTextView.text = item.foodPrice

                binding.itemCountTextView.text = item.fooddQuantity.toString()
            }



//////////////////////////////////
data class AddtoFirebase(val foodName :String?= null,
                         var foodPrice:String?=null,
                         val foodDisc :String?=null,
                         val foodImg :String?=null,
                         val foodIngred :String?= null,
                         var fooddQuantity:Int ?=null,
    var baseprice:String?=null// act as base price for food item for adding that item

)

  
