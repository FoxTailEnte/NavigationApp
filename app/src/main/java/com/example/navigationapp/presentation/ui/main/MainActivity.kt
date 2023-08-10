package com.example.navigationapp.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationapp.R
import com.example.navigationapp.databinding.ActivityMainBinding
import com.example.navigationapp.presentation.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, MainFragment())
            .commit()
    }
}