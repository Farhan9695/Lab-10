package com.farhan.lab_10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.farhan.lab_10.databinding.ActivityProfileBinding
import com.farhan.lab_10.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        binding.nameTextView.text = intent.getStringExtra("name")
        binding.cityTextView.text = intent.getStringExtra("city")
        binding.countryTextView.text = intent.getStringExtra("country")
        binding.phoneNumberTextView.text = intent.getStringExtra("phone")
        binding.emailTextView.text = intent.getStringExtra("email")
        binding.passwordTextView.text = intent.getStringExtra("password")

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}