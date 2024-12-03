package com.farhan.lab_10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.farhan.lab_10.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root

        //Retrieve the connection to Firebase Authentication (Connect to Firebase Auth)
        auth = FirebaseAuth.getInstance()

        db = FirebaseFirestore.getInstance()

        setContentView(view)

        binding.signupButton.setOnClickListener {
            val email = binding.regEmailEditText.text.toString()
            val password = binding.regPasswordEditText.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val newPerson = hashMapOf(
                            "name" to binding.regNameEditText.text.toString().trim(),
                            "city" to binding.regCityEditText.text.toString().trim(),
                            "country" to binding.regCountryEditText.text.toString().trim(),
                            "phone" to binding.regPhoneEditText.text.toString().trim(),
                            "email" to binding.regEmailEditText.text.toString().trim()
                        )

                        db.collection("users")
                            .document(auth.currentUser!!.uid)
                            .set(newPerson)
                        Snackbar.make(
                            binding.root, "Successfully Registered",
                            Snackbar.LENGTH_LONG
                        ).show()
                        finish()
                    } else {
                        Snackbar.make(
                            binding.root, "Registration Failed",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }

        }

        binding.signupButton.setOnClickListener {

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("email", binding.regEmailEditText.text.toString())
            intent.putExtra("password", binding.regPasswordEditText.text.toString())
            intent.putExtra("name", binding.regNameEditText.text.toString())
            intent.putExtra("city", binding.regCityEditText.text.toString())
            intent.putExtra("country", binding.regCountryEditText.text.toString())
            intent.putExtra("phone", binding.regPhoneEditText.text.toString())

            startActivity(intent)

        }
        binding.signupButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}