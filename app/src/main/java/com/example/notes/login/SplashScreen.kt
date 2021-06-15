package com.example.notes.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.navigation.findNavController
import com.example.notes.MainActivity
import com.example.notes.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    }
}