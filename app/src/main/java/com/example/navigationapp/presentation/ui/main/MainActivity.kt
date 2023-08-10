package com.example.navigationapp.presentation.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationapp.R
import com.example.navigationapp.databinding.ActivityMainBinding
import com.example.navigationapp.presentation.ui.fragments.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLog", "Activity Create")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, HomeFragment())
            .commit()
    }
}