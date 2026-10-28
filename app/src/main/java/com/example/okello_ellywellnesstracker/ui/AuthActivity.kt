package com.example.okello_ellywellnesstracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.okello_ellywellnesstracker.databinding.ActivityAuthBinding
import com.example.okello_ellywellnesstracker.data.AppDatabase
import com.example.okello_ellywellnesstracker.data.UserRepository
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)
        userRepository = UserRepository(db.userDao())

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showError(getString(com.example.okello_ellywellnesstracker.R.string.auth_error_empty))
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = userRepository.login(username, password)
                if (result.isSuccess) {
                    val loggedInUser = result.getOrNull() ?: username
                    val intent = Intent(this@AuthActivity, HomeActivity::class.java).apply {
                        putExtra("EXTRA_USERNAME", loggedInUser)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    showError(getString(com.example.okello_ellywellnesstracker.R.string.auth_error_invalid))
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                showError(getString(com.example.okello_ellywellnesstracker.R.string.auth_error_empty))
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val result = userRepository.signUp(username, password)
                if (result.isSuccess) {
                    showError(getString(com.example.okello_ellywellnesstracker.R.string.signup_success))
                } else {
                    showError(result.exceptionOrNull()?.message ?: getString(com.example.okello_ellywellnesstracker.R.string.auth_error_invalid))
                }
            }
        }
    }

    private fun showError(message: String) {
        binding.tvError.text = message
        binding.tvError.visibility = View.VISIBLE
    }
}
