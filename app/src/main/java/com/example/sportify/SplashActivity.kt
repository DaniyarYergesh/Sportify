package com.example.sportify

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import com.example.sportify.databinding.ActivitySplashBinding
import com.example.sportify.presentation.LoginFragment
import com.example.sportify.presentation.RegistrationFragment

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashView.setOnClickListener {
            val fragment = LoginFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()

            it.visibility = GONE
        }
    }

}