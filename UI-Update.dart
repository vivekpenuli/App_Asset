// Proble UI Update
private fun pendingOrders() {
        database = FirebaseDatabase.getInstance()
        var pendingOrderReference = database.reference.child("OrderDetails")
        var pendingOrderItemCount = 0
        pendingOrderReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

pendingOrderItemCount =snapshot.childrenCount.toInt()
                binding.pendingOrdersCount.text = pendingOrderItemCount.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
/*
The code you provided retrieves the pending order count from the Firebase Realtime
Database and updates it in the app's UI. However, it appears that it's only updating when you restart the app.
To make it update in real-time as data changes in the database, you should use Firebase Realtime Database listeners. 
Here's how you can modify your code to achieve real-time updates:
*/


//Solution
private fun pendingOrders() {
    database = FirebaseDatabase.getInstance()
    val pendingOrderReference = database.reference.child("OrderDetails")
    val binding = // your binding reference

    // Create a ValueEventListener to listen for changes
    val valueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val pendingOrderItemCount = snapshot.childrenCount.toInt()
            binding.pendingOrdersCount.text = pendingOrderItemCount.toString()
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle the error here if needed
        }
    }

    // Add the ValueEventListener to the reference
    pendingOrderReference.addValueEventListener(valueEventListener)
}
// It ensure UI will update instanteaneiuly on any new change
