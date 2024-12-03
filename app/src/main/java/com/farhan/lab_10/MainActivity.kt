package com.farhan.lab_10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.emptyLongSet
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.farhan.lab_10.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            if (email.isEmpty()) {
                startActivity(intent)
                Snackbar.make(
                    binding.root, "Please enter your email address",
                    Snackbar.LENGTH_LONG
                ).setTextColor(resources.getColor(R.color.white)).
                setBackgroundTint(resources.getColor(R.color.red)).
                show()
            } else {

                binding.loginButton.setOnClickListener {
                    val password = binding.passwordEditText.text.toString().trim()
                    if (email.isEmpty()) {
                        startActivity(intent)
                        Snackbar.make(
                            binding.root, "Please enter your password",
                            Snackbar.LENGTH_LONG
                        ).setTextColor(resources.getColor(R.color.white)).
                        setBackgroundTint(resources.getColor(R.color.red)).
                        show()
                    } else {

                        binding.loginButton.setOnClickListener {

                            val email = binding.emailEditText.text.toString()
                            val password = binding.passwordEditText.text.toString()

                            auth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        val intent = Intent(this, ProfileActivity::class.java)
                                        startActivity(intent)
                                        Snackbar.make(
                                            binding.root, "Login Successful",
                                            Snackbar.LENGTH_LONG
                                        ).setTextColor(resources.getColor(R.color.black)).
                                        setBackgroundTint(resources.getColor(R.color.green))
                                            .show()

                                    } else {
                                        Snackbar.make(
                                            binding.root, "Login Failed",
                                            Snackbar.LENGTH_LONG
                                        ).setTextColor(resources.getColor(R.color.white)).
                                        setBackgroundTint(resources.getColor(R.color.red))
                                            .show()

                                    }
                                }
                        }
                    }

                }
            }
        }
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}