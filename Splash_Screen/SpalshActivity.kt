package com.example.food_admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

/*
The role of splash screen is to provide first U intraction of application to user
*/
class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val intent = Intent(this,Login_activity::class.java)          // Moving to Login activity 
            startActivity(intent)
            finish()
        },3000)     // FTime duration of Splash screen
    }
}
