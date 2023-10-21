  // Handle click on "Add" button
            binding.addButton.setOnClickListener {
                item.fooddQuantity = item.fooddQuantity!! + 1

                val currentPrice = item.foodPrice?.toIntOrNull() ?: 0
                item.foodPrice = (currentPrice + (item.baseprice?.toInt() ?: 0)).toString()
                println("base price ${item.baseprice?.toInt() ?: 0}")
                binding.dishCostTextView.text = item.foodPrice

                binding.itemCountTextView.text = item.fooddQuantity.toString()
            }
